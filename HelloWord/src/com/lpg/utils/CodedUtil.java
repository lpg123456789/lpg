package com.lpg.utils;

import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.io.Charsets;
import org.apache.log4j.Logger;
import org.apache.mina.util.Base64;

public class CodedUtil {
    /**
     * Logger for this class
     */
    private static Logger log = Logger.getLogger(CodedUtil.class);

    private static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    public static String decodeBase64(String b64string) {
        return new String(Base64.decodeBase64(b64string.getBytes()), Charsets.UTF_8);
    }

    public static String encodeBase64(String stringsrc) {
        Base64 base64encode = new Base64();
        return new String(base64encode.encode(stringsrc.getBytes(Charsets.UTF_8)));
    }

    public static char[] base64Encode(byte[] data) {
        char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
        char[] out = new char[(data.length + 2) / 3 * 4];
        int i = 0;
        for (int index = 0; i < data.length; index += 4) {
            // boolean quad = false;
            // boolean trip = false;
            int val = 0xFF & data[i];
            val <<= 8;
            if (i + 1 < data.length) {
                val |= 0xFF & data[(i + 1)];
                // trip = true;
            }
            val <<= 8;
            if (i + 2 < data.length) {
                val |= 0xFF & data[(i + 2)];
                // quad = true;
            }
            out[(index + 3)] = alphabet[64];
            val >>= 6;
            out[(index + 2)] = alphabet[64];
            val >>= 6;
            out[(index + 1)] = alphabet[(val & 0x3F)];
            val >>= 6;
            out[(index + 0)] = alphabet[(val & 0x3F)];

            i += 3;
        }

        return out;
    }

    /**
     * 小米加密算法
     * 
     * @param encryptText
     * @param encryptKey
     * @return
     */
    public static String generateSignature(String encryptText, String encryptKey) {
        try {
            byte[] data = encryptKey.getBytes(Charsets.UTF_8);
            // 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
            SecretKey secretKey = new SecretKeySpec(data, "HmacSHA1");
            // 生成一个指定 Mac 算法 的 Mac 对象
            Mac mac = Mac.getInstance("HmacSHA1");
            // 用给定密钥初始化 Mac 对象
            mac.init(secretKey);
            byte[] text = encryptText.getBytes(Charsets.UTF_8);
            // 完成 Mac 操作
            byte[] digest = mac.doFinal(text);
            StringBuilder sBuilder = bytesToHexString(digest);
            return sBuilder.toString();
        } catch (Exception e) {
            log.error("算法异常", e);
            return null;
        }
    }

    /**
     * 转换成Hex
     * 
     * @param bytesArray
     */
    public static StringBuilder bytesToHexString(byte[] bytesArray) {
        if (bytesArray == null) {
            return null;
        }
        StringBuilder sBuilder = new StringBuilder();
        for (byte b : bytesArray) {
            String hv = String.format("%02x", b);
            sBuilder.append(hv);
        }
        return sBuilder;
    }

    public static String Md5(String s) {
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            log.error(e, e);
        }
        return null;
    }

    public static String generateSignature_OPPO(String data, String data1) {
        try {
            byte[] byteHMAC = (byte[]) null;
            try {
                Mac mac = Mac.getInstance("HmacSHA1");
                SecretKeySpec spec = null;
                spec = new SecretKeySpec(data.getBytes(), "HmacSHA1");
                mac.init(spec);
                byteHMAC = mac.doFinal(data1.getBytes());
            } catch (InvalidKeyException e) {
                log.error(e, e);
            } catch (NoSuchAlgorithmException e) {
                log.error(e, e);
            }
            return URLEncoder.encode(String.valueOf(base64Encode_OPPO(byteHMAC)), "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

    public static char[] base64Encode_OPPO(byte[] data) {
        final char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
        char[] out = new char[((data.length + 2) / 3) * 4];
        for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
            boolean quad = false;
            boolean trip = false;
            int val = (0xFF & (int) data[i]);
            val <<= 8;
            if ((i + 1) < data.length) {
                val |= (0xFF & (int) data[i + 1]);
                trip = true;
            }
            val <<= 8;
            if ((i + 2) < data.length) {
                val |= (0xFF & (int) data[i + 2]);
                quad = true;
            }
            out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
            val >>= 6;
            out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
            val >>= 6;
            out[index + 1] = alphabet[val & 0x3F];
            val >>= 6;
            out[index + 0] = alphabet[val & 0x3F];
        }
        return out;
    }
}
