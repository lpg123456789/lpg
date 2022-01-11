package com.lpg.utils;

import java.io.IOException;

import org.apache.log4j.Logger;

/**
 * 数据压缩工具
 * 
 * @author zhangzhen
 * @date 2017年8月24日
 * @version 1.0
 */
public class DataZipUtil {

    protected static Logger log = Logger.getLogger(DataZipUtil.class);

    private static final String Version = "20170824";

    private static String dataKey() {
        return "#" + Version + "#";
    }

    /**
     * 数据编码
     * 
     * @param encodeString
     * @return
     */
    public static String dataEncode(String encodeString) {
        return dataEncode(encodeString, 1024);
    }

    private static String dataEncode(String encodeString, int clen) {
        if (encodeString.length() > clen && !encodeString.startsWith(dataKey())) {
            try {
                return dataKey() + CodedUtil.encodeBase64(ZipUtil.compress(encodeString));
            } catch (IOException e) {
                log.error(e, e);
            }
            return encodeString;
        } else {
            return encodeString;
        }
    }

    /**
     * 数据解码
     * 
     * @param decodeString
     * @return
     * @throws Exception
     */
    public static String dataDecode(String decodeString) throws Exception {
        if (decodeString.startsWith(dataKey())) {
            String parseString = decodeString.replaceFirst(dataKey(), "");
            return ZipUtil.uncompress(CodedUtil.decodeBase64(parseString));
        } else {
            return decodeString;
        }
    }

}
