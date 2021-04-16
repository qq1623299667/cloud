package com.example.gateway.util;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 参考地址：https://www.cnblogs.com/only-jlk/p/5960900.html
 */
@Slf4j
public class RSAUtil {

    public static final String KEY_ALGORITHM = "RSA";
    public static final String SIGNATURE_ALGORITHM="MD5withRSA";

    /**
     * 字符串转公钥
     * @author Jia Shi
     * @since 2020/12/9
     */
    public static PublicKey str2PublicKey(String publicKeyString) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(publicKeyString);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

    /**
     * 字符串转私钥
     * @author Jia Shi
     * @since 2020/12/9
     */
    public static PrivateKey str2PrivateKey(String privateKeyString) throws Exception {
        byte[] keyBytes;
        keyBytes = (new BASE64Decoder()).decodeBuffer(privateKeyString);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * RSA公私钥生成
     * @author Jia Shi
     * @since 2020/12/9
     */
    public static KeyPair getRSAKeyPair(Integer size) throws NoSuchAlgorithmException {
        //获得对象 KeyPairGenerator 参数 RSA 1024个字节
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(size);
        //通过对象 KeyPairGenerator 生成密匙对 KeyPair
        KeyPair keyPair = keyPairGen.generateKeyPair();
        return keyPair;
    }

    //***************************签名和验证*******************************
    public static String sign(byte[] data,PrivateKey priK) throws Exception{
        Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
        sig.initSign(priK);
        sig.update(data);
        byte[] sign = sig.sign();
        return Base64Utils.encodeToString(sign);
    }

    public static boolean verify(String str,String sign,PublicKey pubK) throws Exception{
        byte[] data = str.getBytes();
        Signature sig = Signature.getInstance(SIGNATURE_ALGORITHM);
        sig.initVerify(pubK);
        sig.update(data);
        return sig.verify(Base64Utils.decodeFromString(sign));
    }

    //************************加密解密**************************
    public static String encrypt(String plaintext,PublicKey publicKey)throws Exception{
        byte[] bt_plaintext=plaintext.getBytes();
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] bt_encrypted = cipher.doFinal(bt_plaintext);
        return Base64Utils.encodeToString(bt_encrypted);
    }

    public static String decrypt(String encrypted,PrivateKey privateKey)throws Exception{
        byte[] bt_encrypted=Base64Utils.decodeFromString(encrypted);
        Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bt_original = cipher.doFinal(bt_encrypted);
        return new String(bt_original);
    }

        /**
         * 私钥转字符串
         * @author Jia Shi
         * @since 2020/12/9
         */
    public static String privateKey2Str(PrivateKey privateKey){
        String privateKeyString = Base64Utils.encodeToString(privateKey.getEncoded());
        log.info(">>> 私钥转成的字符串是 {} <<<",privateKeyString);
        return privateKeyString;
    }

    /**
     * 公钥转字符串
     * @author Jia Shi
     * @since 2020/12/9
     */
    public static String publicKey2Str(PublicKey publicKey){
        String publicKeyString = Base64Utils.encodeToString(publicKey.getEncoded());
        log.info(">>> 公钥转成的字符串是 {} <<<",publicKeyString);
        return publicKeyString;
    }


    //********************main函数：加密解密和签名验证*********************
    public static void main(String[] args) throws Exception {
        KeyPair rsaKeyPair = getRSAKeyPair(2048);
        String str_pubK= publicKey2Str(rsaKeyPair.getPublic());
        String str_priK= privateKey2Str(rsaKeyPair.getPrivate());
        String str_plaintext = "这是一段用来测试密钥转换的明文这是一段用来测试密钥转换的明文这是一段用来测试密钥转换的明文这是一段用来测试密钥转换的明文这是一段用来测试密钥转换的明文这是一段用来测试密钥转换的明文这是一段用来测试密钥转换的明文这是一段用来测试密钥转换的明文这是一段用来测试密钥转换的明文这是一段用来测试密钥转换的明文这是一段用来测试密钥转换的明文这是一段用来测试密钥转换的明文这是一段用来测试密钥转换的明文这是一段用来测试密钥转换的明文这是一段用来测试密钥转换的明文这是一段用来测试密钥转换的明文";
        log.info("明文："+str_plaintext);
        String encrypt = encrypt(str_plaintext, str2PublicKey(str_pubK));
        log.info("加密后："+encrypt);

        String str_original = decrypt(encrypt,str2PrivateKey(str_priK));
        log.info("解密结果:"+str_original);

        String str="被签名的内容";
        log.info("原文:"+str);
        String signature=sign(str.getBytes(),str2PrivateKey(str_priK));
        log.info("产生签名："+ signature);
        boolean status=verify(str, signature,str2PublicKey(str_pubK));
        log.info("验证情况："+status);
    }


    /**
     *
     * @author Jia Shi
     * @since 2020/12/9
     */
    @Data
    public static class RSAKey {
        private RSAPublicKey publicKey;
        private RSAPrivateKey privateKey;
    }
}