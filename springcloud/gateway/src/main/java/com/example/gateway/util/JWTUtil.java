package com.example.gateway.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;


/**
 *
 * @author Jia Shi
 * @since 2020/12/9
 */
@Slf4j
public class JWTUtil {
    public static final String[] AUDIENCE = {"app","web"};
    public static final String ISSUER = "rstyro";
    public static final String SUBJECT = "test";
    public static String privateKeyString;
    public static String publicKeyString;

    /**
     * 创建token
     * @author Jia Shi
     * @since 2020/12/9
     */
    public static String createToken(String userId,Date beginTime) throws Exception {
            RSAPublicKey publicKey = (RSAPublicKey) RSAUtil.str2PublicKey(publicKeyString);
            RSAPrivateKey privateKey = (RSAPrivateKey) RSAUtil.str2PrivateKey(privateKeyString);
            Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
            Date endTime = DateUtils.offset(beginTime, 2, Calendar.HOUR_OF_DAY);
            String token = JWT.create()
                    .withIssuer(ISSUER)   //发布者
                    .withSubject(SUBJECT)    //主题
                    .withAudience(AUDIENCE)     //观众，相当于接受者
                    .withIssuedAt(beginTime)   // 生成签名的时间
                    .withExpiresAt(endTime)    // 生成签名的有效期,小时
                    .withClaim("userId", userId) //自定义字段存数据
                    .withNotBefore(beginTime)  //生效时间
                    .withJWTId(UUID.randomUUID().toString())    //编号
                    .sign(algorithm);
            return token;
    }

    /**
     * token 验证
     * @author Jia Shi
     * @since 2020/12/9
     */
    public static String verify(String token) throws Exception {
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) RSAUtil.str2PublicKey(publicKeyString), null);
        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER)
                .build();
        DecodedJWT verify = verifier.verify(token);
        String data = verify.getClaim("userId").asString();
        return data;
    }

    public static void main(String[] args) throws Exception {
        // 正常情况下从数据库查询得到
        KeyPair rsaKeyPair = RSAUtil.getRSAKeyPair(1024);
        privateKeyString = RSAUtil.privateKey2Str(rsaKeyPair.getPrivate());
        publicKeyString = RSAUtil.publicKey2Str(rsaKeyPair.getPublic());
        String userId="123";
        Date beginTime=DateUtils.offset(new Date(), 0, Calendar.HOUR_OF_DAY);
        String token = createToken(userId,beginTime);
        log.info(">>> userId {} 生成的token是 {}  <<<",userId,token);
        String verify = verify(token);
        log.info(">>> token {} 解析出来的数据是 {} <<<",token,verify);
    }
}
