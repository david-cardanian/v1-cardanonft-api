package com.cardanonft.api.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.*;

@Component
public class RSAUtil {
    private static Logger logger = LoggerFactory.getLogger(RSAUtil.class);

    private static final int DEFAULT_KEY_SIZE = 2048;
    private static final String KEY_FACTORY_ALGORITHM = "RSA";

    /**
     * 2048비트 RSA 키쌍을 생성합니다.
     */
    public static KeyPair genRSAKeyPair() throws NoSuchAlgorithmException {

        KeyPairGenerator gen = KeyPairGenerator.getInstance(KEY_FACTORY_ALGORITHM);
        gen.initialize(DEFAULT_KEY_SIZE, new SecureRandom());

        KeyPair keyPair = gen.genKeyPair();

        return keyPair;
    }

    /**
     * Public Key로 RSA 암호화를 수행합니다.
     * @param plainText 암호화할 평문입니다.
     * @param publicKey 공개키 입니다.
     * @return
     */
    public static String encryptRSA(String plainText, PublicKey publicKey) throws Exception {

        Cipher cipher = Cipher.getInstance(KEY_FACTORY_ALGORITHM);

        try {
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] bytePlain = cipher.doFinal(plainText.getBytes());
            return DatatypeConverter.printBase64Binary(bytePlain);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Private Key로 RAS 복호화를 수행합니다.
     *
     * @param encrypted 암호화된 이진데이터를 base64 인코딩한 문자열 입니다.
     * @param privateKey 복호화를 위한 개인키 입니다.
     * @return
     * @throws Exception
     */
    public static String decryptRSA(String encrypted, PrivateKey privateKey) throws Exception {

        Cipher cipher = Cipher.getInstance(KEY_FACTORY_ALGORITHM);

        byte[] byteEncrypted = DatatypeConverter.parseBase64Binary(encrypted);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] bytePlain = cipher.doFinal(byteEncrypted);
        String decrypted = new String(bytePlain, StandardCharsets.UTF_8);

        return decrypted;
    }

}
