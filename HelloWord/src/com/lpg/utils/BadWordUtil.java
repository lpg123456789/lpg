package com.game.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wenxing
 */
public class BadWordUtil {
    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static final Lock readLock = readWriteLock.readLock();
    private static final Lock writeLock = readWriteLock.writeLock();

    private static Logger log = LoggerFactory.getLogger(BadWordUtil.class);

    private final static Map<String, String> NameWordMap = new HashMap<>();

    private final static Map<String, String> ChatWordMap = new HashMap<>();


    /**
     * 本地名字敏感词配置缓存
     */
    private static List<String> localNameWordConfig = new ArrayList<>();

    /**
     * 本地敏感词缓存
     */
    private static List<String> localChatWordConfig = new ArrayList<>();

    /**
     * 远程名字敏感词缓存
     */
    private static List<String> remoteNameWordConfig = new ArrayList<>();

    /**
     * 远程敏感词缓存
     */
    private static List<String> remoteChatWordConfig = new ArrayList<>();


    private static BadWordStrategy badWordStrategy;

    /**
     * 最小匹配规则
     */
    public final static int minMatchTYpe = 1;
    /**
     * 最大匹配规则
     */
    public final static int maxMatchType = 2;

    /**
     * 聊天
     */
    public final static int wordTypeChat = 1;
    /**
     * 名字
     */
    public final static int wordTypeName = 2;

    static {
        badWordStrategy =BadWordStrategyType.getStrategyByVer(Globals.LANGUAGE_VERSION);
    }


    /**
     * 更新本地聊天敏感词
     * @throws Exception
     */
    public static void updateLocalChatBadWord() {
        List<B_filter_bs_Bean> list = ManagerPool.getInstance().dataManager.c_filter_bs_Contailner.getList();
        List<String> badWords = new ArrayList<>(list.size());
        for (B_filter_bs_Bean filterBsBean : list) {
            badWords.add(filterBsBean.getName().toLowerCase());
        }
        localChatWordConfig = badWords;
        Set<String> keyWordSet = new HashSet<>(badWords);
        keyWordSet.addAll(remoteChatWordConfig);
        addBadWordToHashMap(keyWordSet, ChatWordMap);
    }



    /**
     * 更新远程敏感词
     * @param badWords
     */
    public static void updateRemoteChatBadWord(List<String> badWords) {
        if (badWords == null) {
            log.warn("update remote chat bad word is null");
            return;
        }
        remoteChatWordConfig = badWords;
        Set<String> keyWordSet = new HashSet<>(badWords);
        keyWordSet.addAll(localChatWordConfig);
        addBadWordToHashMap(keyWordSet, ChatWordMap);
    }


    /**
     * 更新远程名字敏感词
     * @param badWords
     */
    public static void updateRemoteNameBadWord(List<String> badWords) {
        if (badWords == null) {
            log.warn("update remote name bad word is null");
            return;
        }
        remoteNameWordConfig = badWords;
        Set<String> keyWordSet = new HashSet<>(badWords);
        keyWordSet.addAll(localNameWordConfig);
        addBadWordToHashMap(keyWordSet, NameWordMap);
    }

    /**
     * 更新本地名字敏感词
     * @throws Exception
     */
    public static void updateLocalNameBadWord() {
        List<B_name_sensitive_bs_Bean> list = ManagerPool.getInstance().dataManager.c_name_sensitive_bs_Contailner.getList();
        List<String> nameBadWords = new ArrayList<>(list.size());
        for (B_name_sensitive_bs_Bean nameSensitiveBsBean : list) {
            nameBadWords.add(nameSensitiveBsBean.getName().toLowerCase());
        }
        localNameWordConfig = nameBadWords;
        Set<String> keyWordSet = new HashSet<>(nameBadWords);
        keyWordSet.addAll(remoteNameWordConfig);
        addBadWordToHashMap(keyWordSet, NameWordMap);
    }


    /**
     * 检查文字中是否包含敏感字符，检查规则如下：<br>
     *
     * @param txt
     * @param beginIndex
     * @param matchType
     * @param wordType
     * @return 如果存在 返回敏感词字符的长度，不存在返回0
     */
    @SuppressWarnings("rawtypes")
    private static int checkBadWord(String txt, int beginIndex, int matchType, int wordType) {
        readLock.lock();
        try {
            return badWordStrategy.checkBadWord(txt,beginIndex,matchType,wordType);
        } catch (Exception e) {
            log.error("checkBadWord error.", e);
        } finally {
            readLock.unlock();
        }
        return 0;
    }

    /**
     * 检查文字中是否包含敏感字符，检查规则如下：<br>
     *
     * @param txt
     * @param beginIndex
     * @param matchType
     * @param wordType
     * @return 如果存在 返回敏感词字符的长度，不存在返回0
     */
    @SuppressWarnings("rawtypes")
    private static int checkBadWordJumpWord(String txt, int beginIndex, int matchType, int wordType) {
        // 敏感词结束标识位：用于敏感词只有1位的情况
        boolean flag = false;
        boolean match = false;
        // 匹配标识数默认为0
        int matchLength = 0;
        int jumpLen = 0;
        char word = 0;
        List<Map> list=new ArrayList<>();
        readLock.lock();
        try {
            Map nowMap = wordType == wordTypeChat ? ChatWordMap : NameWordMap;
            for (int i = beginIndex; i < txt.length(); i++) {
                word = txt.charAt(i);
                Map newMap;
                if(match){
                    newMap=getMap(list,word);
                }else{
                    newMap= (Map) nowMap.get(word);
                }
                if (match&&newMap==null) {
                    // 空格 不进行判断 但是会占用长度
                    matchLength++;
                    if(++jumpLen>2){
                        break;
                    }
                    continue;
                }
                // 获取指定key
                //nowMap = (Map) nowMap.get(word);
                if (newMap == null) {
                    // 不存在，直接返回
                    break;
                }
                // 存在，则判断是否为最后一个
                // 找到相应key，匹配标识+1
                matchLength++;
                match=true;
                jumpLen=0;
                // 如果为最后一个匹配规则,结束循环，返回匹配标识数
                if ("1".equals(newMap.get("isEnd"))) {
                    // 结束标志位为true
                    flag = true;
                    break;
                }else{
                    list.add(newMap);
                }
            }
        } catch (Exception e) {
            log.error("checkBadWord error.", e);
        } finally {
            readLock.unlock();
        }
        if (!flag) {
            matchLength = 0;
        }
        return matchLength;
    }

    private static Map getMap(List<Map> list,char key){
        for(Map map:list){
            if(map.containsKey(key)){
                return (Map)map.get(key);
            }
        }
        return null;
    }

    /**
     * 判断文字是否包含敏感字符
     *
     * @param txt       文字
     * @param matchType 匹配规则 1：最小匹配规则，2：最大匹配规则
     * @return 若包含返回true，否则返回false
     */
    public static boolean isContaintBadWord(String txt, int matchType, int wordType) {
        // 转为小写
        txt = txt.toLowerCase();
        for (int i = 0; i < txt.length(); i++) {
            // 判断是否包含敏感字符
            int matchFlag = checkBadWord(txt, i, matchType, wordType);
            // 大于0存在，返回true
            if (matchFlag > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 替换敏感字字符
     *
     * @param sourceContnt
     * @param matchType
     * @param wordType
     * @param replaceChar  替换字符，默认*
     * @version 1.0
     */
    public static String replaceBadWord(String sourceContnt, int matchType, int wordType, String replaceChar) {
        // 去除掉2端空格
        sourceContnt = sourceContnt.trim();
        // 获取所有的敏感词
        try {
            List<Pair<Integer, Integer>> badWordIndexs = getBadWordIndexs(sourceContnt, matchType, wordType);
            while (!badWordIndexs.isEmpty()){
                StringBuilder resultTxt = new StringBuilder(sourceContnt);
                for (Pair<Integer, Integer> pair : badWordIndexs) {
                    int startIndex = pair.getKey();
                    int endIndex = pair.getValue();
                    int length = endIndex - startIndex;
                    if (length <= 0) {
                        continue;
                    }
                    String replaceString = buildReplaceChars(replaceChar, length);
                    resultTxt.replace(startIndex, endIndex, replaceString);
                    sourceContnt=resultTxt.toString();
                    badWordIndexs=getBadWordIndexs(sourceContnt, matchType, wordType);
                }
            }
//            if (badWordIndexs == null || badWordIndexs.isEmpty()) {
//                return sourceContnt;
//            }

            return sourceContnt;
        } catch (Exception e) {
            log.error("replaceBadWord[" + sourceContnt + "] error.", e);
        }
        return StringUtils.EMPTY;
    }

    /**
     * 获取文字中的敏感词在原始文本中的开始结束下标
     *
     * @param originContext 原始文字
     * @param matchType     匹配规则 1：最小匹配规则，2：最大匹配规则
     * @param wordType
     * @return
     */
    private static List<Pair<Integer, Integer>> getBadWordIndexs(final String originContext, int matchType, int wordType) {
        List<Pair<Integer, Integer>> resultList = new ArrayList<>();
        // 转为小写 方便过滤
        String checkContent = originContext.toLowerCase();
        for (int index = 0; index < originContext.length(); index++) {
            char c = originContext.charAt(index);
            if (Character.isWhitespace(c)) {
                // 不从空格开始查找
                continue;
            }
            if(c=='*'){
                continue;
            }
            // 判断是否包含敏感字符
            int length = checkBadWord(checkContent, index, matchType, wordType);
            // 存在,加入list中
            if (length > 0) {
                int endIndex = index + length;
                resultList.add(Pair.of(index, endIndex));
                // 减1的原因，是因为for会自增
                index = endIndex - 1;
            }
        }
        return resultList;
    }

    /**
     * 获取文字中的敏感词
     *
     * @param originContext 原始文字
     * @param matchType     匹配规则 1：最小匹配规则，2：最大匹配规则
     * @param wordType
     */
    public static Set<String> getBadWordSets(final String originContext, int matchType, int wordType) {
        Set<String> sensitiveWordList = new HashSet<>();
        // 转为小写 方便过滤
        String checkContent = originContext.toLowerCase();
        for (int i = 0; i < originContext.length(); i++) {
            char c=originContext.charAt(i);
            if (Character.isWhitespace(c)) {
                // 不从空格开始查找
                continue;
            }
            if(c=='*'){
                continue;
            }
            // 判断是否包含敏感字符
            int length = checkBadWord(checkContent, i, matchType, wordType);
            // 存在,加入list中
            if (length > 0) {
                // 过滤时 使用原文本内容
                int endIndex = i + length;
                sensitiveWordList.add(originContext.substring(i, endIndex));
                // 减1的原因，是因为for会自增
                i = endIndex - 1;
            }
        }
        return sensitiveWordList;
    }

    /**
     * 获取替换字符串
     *
     * @param replaceChar
     * @param length
     */
    private static String buildReplaceChars(String replaceChar, int length) {
        if (length == 1) {
            return replaceChar;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append(replaceChar);
        }
        return sb.toString();
    }

    /**
     * 敏感词库构建成了一个类似与一颗一颗的树，这样我们判断一个词是否为敏感词时就大大减少了检索的匹配范围。
     *
     * @param keyWordSet 敏感词库
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static void addBadWordToHashMap(Set<String> keyWordSet, Map<String, String> rootMap) {
        // 初始化敏感词容器，减少扩容操作
        Map newRootMap = new HashMap<>();
        Map nowMap = null;
        Map<String, String> newWorMap = null;
        // 迭代keyWordSet
        Iterator<String> iterator = keyWordSet.iterator();
        while (iterator.hasNext()) {
            // 关键字
            String key = iterator.next();
            nowMap = newRootMap;
            for (int i = 0; i < key.length(); i++) {
                // 转换成char型
                char keyChar = key.charAt(i);
                // 获取
                Object wordMap = nowMap.get(keyChar);
                // 如果存在该key，直接赋值
                if (wordMap != null) {
                    nowMap = (Map) wordMap;
                }
                // 不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                else {
                    newWorMap = new HashMap<>();
                    // 不是最后一个
                    newWorMap.put("isEnd", "0");
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }

                if (i == key.length() - 1) {
                    // 最后一个
                    nowMap.put("isEnd", "1");
                }
            }
        }
        writeLock.lock();
        try {
            rootMap.clear();
            rootMap.putAll(newRootMap);
        } catch (Exception e) {
            log.error("addBadWordToHashMap", e);
        } finally {
            writeLock.unlock();
        }
    }

    public static int getWordCount(String name) {
        if (name == null) {
            return 0;
        }
        int length = 0;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            if (isBasicChar(c) || isThai(c)) {
                // 泰文也只计算1个字节
                length++;
            } else {
                length += 2;
            }
        }
        return length;
    }

    /**
     * 该字符是否基础字符(含英文字符)<br>
     *
     * 0000-007F：C0控制符及基本拉丁文 (C0 Control and Basic Latin)<br>
     * 0080-00FF：C1控制符及拉丁文补充-1 (C1 Control and Latin 1 Supplement)
     *
     * @param c
     * @return
     */
    private static boolean isBasicChar(char c) {
        return (c >= 0x0000 && c <= 0x00ff);
    }

    /**
     * 该字符是否泰文
     *
     * @param c
     * @return
     */
    private static boolean isThai(char c) {
        return (c >= 0x0e00 && c <= 0x0e7f);
    }


    /**
     * 基本原理是将字符串中所有的非标准字符（双字节字符）替换成两个标准字符（**，或其他的也可以）。这样就可以直接例用length方法获得字符串的字节长度了
     */
    public static int getWordCountRegex(String s) {
        s = s.replaceAll("[^\\x00-\\xff]", "**");
        int length = s.length();
        return length;
    }

    public static void fixGameBadWord() {
        File file = new File("hotfix/" + com.game.Globals.serverId + "_bad_word_fix.txt");
        try (FileOutputStream os = new FileOutputStream(file)) {
            log.warn("updatePlayerNewName begin.");
            ManagerPool.getInstance().playerManager.fixPlayerNameBadWord(os);
            log.warn("updatePlayerNewName end.");
            log.warn("updatePetName start.");
            ManagerPool.getInstance().petManager.fixPetNameBadWord(os);
            log.warn("updatePetName end.");
            log.warn("updateAlliance start.");
            ManagerPool.getInstance().allianceManager.fixAllianceBadWord(os);
            log.warn("updateAlliance end.");
        } catch (Exception e) {
            log.error("fixGameBadWord error", e);
        }
    }

    public static Map<String, String> getNameWordMap() {
        return NameWordMap;
    }

    public static Map<String, String> getChatWordMap() {
        return ChatWordMap;
    }

    public static boolean isChinese(char c){
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
        ){
            return true;
        }
        return false;
    }

    /**
     * 是否是英文
     * @param c
     * @return
     */
    public static boolean isEnglish(char c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }
}
