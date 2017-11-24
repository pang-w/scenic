package com.itmaoo.oa.support;

import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.*;

/**
 * 加解密工具包
 */
public class CryptoUtil {

    /**
     * MD5算法名
     */
    private static final String MD5_ALGORITHM = "MD5";

    /**
     * 3DES算法名
     */
    private static final String DES_ALGORITHM = "DESede";

    /**
     * AES算法名
     */
    private static final String AES_ALGORITHM = "AES";

    /**
     * 3DES加密模式
     */
    private static final String DES_ALGORITHM_MODE = "/CBC/PKCS5Padding";

    /**
     * 编码方式
     */
    private static final String CHARSET_NAME = "UTF-8";



    /**
     * Md5签名
     *
     * @param input 签名明文
     * @return 签名结果
     */
    public static String md5Sign(String input) {
        return md5Sign(input, CHARSET_NAME);
    }

    /**
     * Md5签名
     *
     * @param input   签名明文
     * @param charset 编码名称
     * @return 签名结果
     */
    public static String md5Sign(String input, String charset) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance(MD5_ALGORITHM);
            messageDigest.reset();
            messageDigest.update(input.getBytes(charset));

            byte[] byteArray = messageDigest.digest();
            StringBuffer md5StrBuff = new StringBuffer();
            for (int i = 0; i < byteArray.length; i++) {
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
                } else {
                    md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
                }
            }
            return md5StrBuff.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 3DES加密
     *
     * @param input  加密明文
     * @param desKey 3DES密钥
     * @param desIv  3DES向量
     * @return 加密结果
     */
    public static String triDesEncrypt(String input, String desKey, String desIv) {
        Cipher cipher = null;
        try {
            SecureRandom sr = new SecureRandom();
            DESedeKeySpec dks = new DESedeKeySpec(desKey.getBytes(CHARSET_NAME));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
            Key key = keyFactory.generateSecret(dks);
            IvParameterSpec iv = new IvParameterSpec(desIv.getBytes(CHARSET_NAME));
            cipher = Cipher.getInstance(DES_ALGORITHM + DES_ALGORITHM_MODE);
            cipher.init(Cipher.ENCRYPT_MODE, key, iv, sr);
            byte[] array = cipher.doFinal(input.getBytes(CHARSET_NAME));
            return Base64.encodeBase64String(array);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 3DES解密
     *
     * @param input  3DES密文
     * @param desKey 3DES密钥
     * @param desIv  3DES向量
     * @return 解密结果
     */
    public static String triDesDecrypt(String input, String desKey, String desIv) {
        Cipher cipher = null;
        try {
            SecureRandom sr = new SecureRandom();
            DESedeKeySpec dks = new DESedeKeySpec(desKey.getBytes(CHARSET_NAME));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES_ALGORITHM);
            SecretKey key = keyFactory.generateSecret(dks);
            IvParameterSpec iv = new IvParameterSpec(desIv.getBytes(CHARSET_NAME));
            cipher = Cipher.getInstance(DES_ALGORITHM + DES_ALGORITHM_MODE);
            cipher.init(Cipher.DECRYPT_MODE, key, iv, sr);
            byte[] array = cipher.doFinal(new Base64().decode(input));
            return new String(array, CHARSET_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static SecretKeySpec readSecretKey(InputStream inStream) throws Exception {
        byte[] keyData = readFile(inStream);
        SecretKeySpec key = new SecretKeySpec(keyData, AES_ALGORITHM);
        return key;
    }

    public static SecretKeySpec readSecretKey(byte[] secretKey) throws Exception {
        SecretKeySpec key = new SecretKeySpec(secretKey, AES_ALGORITHM);
        return key;
    }

    public static String encode64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    public static byte[] decode64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    public static String generateKey() throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance(AES_ALGORITHM);
        kgen.init(128);
        SecretKey secretKey = kgen.generateKey();
        byte[] enCodeFormat = secretKey.getEncoded();
        return encode64(enCodeFormat);
    }

    /**
     * 读取文件内容至字节数组中
     **/
    public static byte[] readFile(InputStream cntInput) throws Exception {
        // FileInputStream cntInput = new FileInputStream(inStream);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int b = -1;
        while ((b = cntInput.read()) != -1) {
            baos.write(b);
        }
        cntInput.close();
        byte[] contents = baos.toByteArray();
        baos.close();
        return contents;
    }
}
