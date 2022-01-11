package com.lpg.utils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;


public class StringUtil {

    /**
     * 字符串比较
     * 
     * @param str1
     * @param str2
     * @return
     */
    public static boolean compareString(String str1, String str2) {
        if (str1 == null || str2 == null) {
            if (str1 == str2) {
                return true;
            }
        } else if (str1.equals(str2)) {
            return true;
        }
        return false;
    }

    /**
     * 字符串比较(忽略大小写)
     * 
     * @param str1
     * @param str2
     * @return
     */
    public static boolean compareStringIgnoreCase(String str1, String str2) {
        if (str1 == null || str2 == null) {
            if (str1 == str2) {
                return true;
            }
        } else if (str1.equalsIgnoreCase(str2)) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符串是否null或者是否为空字符
     * 
     * @param string
     * @return
     */
    public static boolean isEmptyOrNull(String string) {
        if (string == null || "".equals(string)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获得一个JSON的数据 <br>
     * 注意：这里的格式是没有最外层的括号的如下: <br>
     * “x:123, y:123, name:"张三" ”
     * 
     * @param txt
     * @return
     * 
     */
	/*public static String formatToJson(String txt) {
	    txt = txt.substring(1, txt.length() - 1);
	    txt = "{" + txt + "}";
	    txt = txt.replaceAll(Symbol.DOUHAO_REG, Symbol.DOUHAO);
	    String parse = txt.replaceAll("([{,，])\\s*([0-9a-zA-Z一-龟]+)\\s*:", "$1\"$2\":");
	    return parse;
	}*/

    /**
     * 字符串转数组
     * 
     * @param str
     * @param split
     * @return
     */
    public static int[] str2IntArray(String str, String split) {
        if (str == null) {
            return new int[0];
        }
        String[] strs = str.split(split);
        int[] result = new int[strs.length];
        for (int i = 0; i < strs.length; i++) {
            result[i] = Integer.parseInt(strs[i]);
        }
        return result;
    }

    /**
     * 字符串数组转list<Integer>
     */
    public static List<Integer> str2IntegerList(String str, String split) {
        return Arrays.stream(str2IntArray(str, split)).boxed().collect(Collectors.toList());
    }

    /**
     *
     * @param str
     * @param split1
     * @param split2
     * @return
     */
    public static int[][] str2IntDyadicArray(String str, String split1, String split2) {
        if (str == null) {
            return new int[0][];
        }
        String[] str1s = str.split(split1);
        int[][] result = new int[str1s.length][];
        for (int i = 0; i < str1s.length; i++) {
            String str1 = str1s[i];
            if (str1 == null) {
                result[i] = new int[0];
                continue;
            }
            String[] str2s = str1.split(split2);
            if (str2s.length <= 0) {
                result[i] = new int[0];
                continue;
            }
            int[] array2 = new int[str2s.length];
            for (int j = 0; j < str2s.length; j++) {
                String str2 = str2s[j];
                array2[j] = Integer.parseInt(str2);
            }
            result[i] = array2;
        }
        return result;
    }

    /**
     * 字符串转Map<Integer,Integer>
     * 
     * @param str
     * @param split1
     * @param split2
     * @return
     */
    public static Map<Integer, Integer> str2IIMap(String str, String split1, String split2) {
        Map<Integer, Integer> map = new HashMap<>();
        if (str == null || str.isEmpty()) {
            return map;
        }
        String[] str1s = str.split(split1);
        for (String str1 : str1s) {
            if (str1 == null) {
                continue;
            }
            String[] str2s = str1.split(split2);
            if (str2s.length < 2) {
                continue;
            }
            int key = Integer.parseInt(str2s[0]);
            int value = Integer.parseInt(str2s[1]);
            map.put(key, value);
        }
        return map;
    }

    /**
     * map转为str<br>
     * key1 split1 value1 split2 key2 split1 value2
     * 
     * @param map
     * @param split1
     * @param split2
     * @return
     */
    public static String map2Str(Map<Integer, Integer> map, String split1, String split2) {
        if (map == null || map.isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (sb.length() > 0) {
                sb.append(split2);
            }
            sb.append(key).append(split1).append(value);
        }
        return sb.toString();
    }

}
