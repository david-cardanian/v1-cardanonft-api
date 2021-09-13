package com.cardanonft.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.*;
import com.amazonaws.util.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SecretsManagerConfig {
    //DB암호화 키
    private static String DB_SECRET_KEY;
    //AWS Secret Service에 저장된 DB암호화 키의 서비스명
    private static String SECRET_NAME;

    private static String AWS_ACCESS_KEY;

    private static String AWS_SECRET_KEY;

    private static String AWS_SECRET_MANAGER_ENDPOINT;

    private static String AWS_REGION;

    @Value("${db.secret.key}")
    public void setDbSecretKey(String dbSecretKey) {
        this.DB_SECRET_KEY = dbSecretKey;
    }
    @Value("${db.secret.name}")
    public void setSecretName(String secretName) {
        this.SECRET_NAME = secretName;
    }
    @Value("${aws.accessKey}")
    public void setAwsAccessKey(String awsAccessKey) {
        this.AWS_ACCESS_KEY = awsAccessKey;
    }
    @Value("${aws.secretKey}")
    public void setAwsSecretKeyKey(String awsSecretKey) {
        this.AWS_SECRET_KEY = awsSecretKey;
    }
    @Value("${aws.secretManagerEndpoint}")
    public void setAwsSecretManagerEndpoint(String awsSecretManagerEndpoint) {
        this.AWS_SECRET_MANAGER_ENDPOINT = awsSecretManagerEndpoint;
    }
    @Value("${aws.region}")
    public void setRegion(String awsRegion) {
        this.AWS_REGION = awsRegion;
    }
    public String getAwsAccessKey(){
        return this.AWS_ACCESS_KEY;
    }
    public String getAwsSecretKey(){
        return this.AWS_SECRET_KEY;
    }
    /**
     * AWS Secret Service에서 DB암호화 키 가져오기
     */
     private static synchronized String getSecretValueFromAWS(String awsAccessKey, String awsSecretKey, String region , String secretName){
        // Create a Secrets Manager client
        AWSSecretsManager client = null;
        String secret = "";
        GetSecretValueRequest getSecretValueRequest = null;
        GetSecretValueResult getSecretValueResult = null;
        if(DB_SECRET_KEY != null && DB_SECRET_KEY.length() > 0) {
            // DB암호화키가 존재하면 메모리에서 return (이미 한번 AWS Secret Manager에서 가져온경우)
            return DB_SECRET_KEY;
        }else{
            // DB암호화키가 존재하지 않으면 AWS Secret Manager에서 가져옴
            if(awsAccessKey!=null && awsSecretKey!=null && region!=null && secretName!=null){
                // AWS 접속정보를 입력받을 경우
                client  = AWSSecretsManagerClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(AWS_SECRET_MANAGER_ENDPOINT,AWS_REGION))
                        .build();
                getSecretValueRequest = new GetSecretValueRequest()
                        .withSecretId(secretName);
            }else{
                // AWS 접속정보를 입력받지 않을 경우 설정파일에 있는 정보를 이용
                client  = AWSSecretsManagerClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(AWS_ACCESS_KEY, AWS_SECRET_KEY)))
                        .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(AWS_SECRET_MANAGER_ENDPOINT,AWS_REGION))
                        .build();
                getSecretValueRequest = new GetSecretValueRequest()
                        .withSecretId(SECRET_NAME);
            }
        }
        try {
            getSecretValueResult = client.getSecretValue(getSecretValueRequest);
        } catch (DecryptionFailureException e) {
            // Secrets Manager can't decrypt the protected secret text using the provided KMS key.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        } catch (InternalServiceErrorException e) {
            // An error occurred on the server side.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        } catch (InvalidParameterException e) {
            // You provided an invalid value for a parameter.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        } catch (InvalidRequestException e) {
            // You provided a parameter value that is not valid for the current state of the resource.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        } catch (ResourceNotFoundException e) {
            // We can't find the resource that you asked for.
            // Deal with the exception here, and/or rethrow at your discretion.
            throw e;
        }

        // Decrypts secret using the associated KMS CMK.
        // Depending on whether the secret is a string or binary, one of these fields will be populated.
        if (getSecretValueResult.getSecretString() != null) {
            secret = getSecretValueResult.getSecretString();
            JSONParser parser = new JSONParser();
            try {
                JSONObject json = (JSONObject) parser.parse(secret);
                DB_SECRET_KEY = String.valueOf(json.get(SECRET_NAME));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return DB_SECRET_KEY;
    }
    public static String getDbSecretKey(){
        if(!StringUtils.isNullOrEmpty(DB_SECRET_KEY)){
            return DB_SECRET_KEY;
        }else{
            return getSecretValueFromAWS(AWS_ACCESS_KEY, AWS_SECRET_KEY, AWS_REGION, SECRET_NAME);
        }
    }
}

