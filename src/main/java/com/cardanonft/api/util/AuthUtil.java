package com.cardanonft.api.util;

import com.cardanonft.api.uuid.GenerateShortUUID;
import com.cardanonft.api.vo.auth.AuthToken;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class AuthUtil {

	static final String SEED = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	static SecureRandom RAND = new SecureRandom();
	static final int TOKEN_LENGTH_RAND = 16;

	private String generateKeyCandidate(){
		StringBuilder sb = new StringBuilder();
		sb.append(GenerateShortUUID.next());
		for( int i = 0; i < TOKEN_LENGTH_RAND; i++ )
			sb.append( SEED.charAt( RAND.nextInt(SEED.length()) ) );
		return sb.toString();
	}

	private String generateSecretCandidate() {
		KeyGenerator keyGen;
		StringBuilder sb = new StringBuilder();
		try {
			keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			SecretKey secretKey = keyGen.generateKey();
			byte[] encoded = secretKey.getEncoded();
			for (byte b : encoded)
			{
				sb.append(String.format("%2X",b));
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	public AuthToken generateNAccessToken() {
		AuthToken nToken = new AuthToken();
		nToken.setToken(generateKeyCandidate());
		nToken.setSecret(generateSecretCandidate());
		nToken.setRefresh_token(generateKeyCandidate());

		return nToken;
	}

	public int getAccessControlRuleId(int ruleServer) {
        Random random = new Random();
        int accessControlRuleId;
        int bitMask = 1;
        int shiftCount = 0;
        int bitCount = Integer.bitCount(ruleServer);
        List<Integer> serverList = new ArrayList<>();

        if (bitCount == 1)
            return ruleServer;

        while (shiftCount < bitCount) {
            if ((bitMask & ruleServer) != 0) {
                serverList.add(bitMask);
                shiftCount++;
            }
            bitMask <<= 1;
        }

        accessControlRuleId = serverList.get(random.nextInt(bitCount));

		return accessControlRuleId;
	}
}
