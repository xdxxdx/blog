/**
 * @Filename: TLinxAESCoder.java
 * @Author锛歝aiqf
 * @Date锛�016-4-12
 */
package com.zh.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Class: TLinxAESCoder.java
 * @Description: AES加解密类
 * @Author：caiqf
 * @Date：2016-4-12
 */
public class TLinxAESCoder {
    private static String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";
    private static String KEY_ALGORITHM = "AES";

    public static String decrypt(String sSrc, String sKey) throws Exception {
        SecretKeySpec skeySpec = new SecretKeySpec(sKey.getBytes("ASCII"), KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(2, skeySpec);
        byte[] encrypted1 = TLinx2Util.hex2byte(sSrc);
        byte[] original = cipher.doFinal(encrypted1);
        return new String(original, "UTF-8");
    }

    public static String encrypt(String sSrc, String sKey) throws Exception {
        System.out.println("====data加密前的明文= " + sSrc);
        SecretKeySpec skeySpec = new SecretKeySpec(sKey.getBytes("ASCII"), KEY_ALGORITHM);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(1, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes("UTF-8"));
        return TLinx2Util.byte2hex(encrypted);
    }

    public static void main(String[] args) {
        String data = "";
        String str = null;
        try {
            str = decrypt(data, "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(str);
    }
}
