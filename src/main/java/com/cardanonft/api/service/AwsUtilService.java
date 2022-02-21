package com.cardanonft.api.service;


import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by choi on 2017. 5. 19..
 */

@Service
public class AwsUtilService {

    private static Logger logger = LoggerFactory.getLogger(AwsUtilService.class);

    private final AmazonS3 amazonS3;

    public AwsUtilService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String uploadFileByDate(String butcketName, String imgUse, File file, boolean publicYn) throws Exception {
        String key = null;
        try {
            String file_name = UUID.randomUUID() + "-" + file.getName();
            key = this.generateKeyByDate(imgUse, file_name);
            logger.info("Uploading a new object to S3 from a file\n");

            PutObjectRequest request = new PutObjectRequest(butcketName, key, file);

            if(publicYn){
                //  aking the object Public
                request.setCannedAcl(CannedAccessControlList.PublicRead);
                // acl list
                AccessControlList acl = new AccessControlList();
                acl.grantPermission(GroupGrantee.AuthenticatedUsers, Permission.FullControl);
                acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
                request.setAccessControlList(acl);
            }else {
                //  aking the object Public
                request.setCannedAcl(CannedAccessControlList.Private);
                // acl list
                AccessControlList acl = new AccessControlList();
                acl.grantPermission(GroupGrantee.AuthenticatedUsers, Permission.FullControl);
                request.setAccessControlList(acl);
            }
            // upload
            amazonS3.putObject(request);
        } catch (AmazonServiceException ase) {
            logger.error("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            logger.error("Error Message:    " + ase.getMessage());
            logger.error("HTTP Status Code: " + ase.getStatusCode());
            logger.error("AWS Error Code:   " + ase.getErrorCode());
            logger.error("Error Type:       " + ase.getErrorType());
            logger.error("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            logger.error("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            logger.error("Error Message: " + ace.getMessage());
        }
        return key;
    }
    public String uploadFileByKey(String butcketName, String key, File file, boolean publicYn) throws Exception {
        try {
            PutObjectRequest request = new PutObjectRequest(butcketName, key, file);

            if(publicYn){
                //  aking the object Public
                request.setCannedAcl(CannedAccessControlList.PublicRead);
                // acl list
                AccessControlList acl = new AccessControlList();
                acl.grantPermission(GroupGrantee.AuthenticatedUsers, Permission.FullControl);
                acl.grantPermission(GroupGrantee.AllUsers, Permission.Read);
                request.setAccessControlList(acl);
            }else {
                //  aking the object Public
                request.setCannedAcl(CannedAccessControlList.Private);
                // acl list
                AccessControlList acl = new AccessControlList();
                acl.grantPermission(GroupGrantee.AuthenticatedUsers, Permission.FullControl);
                request.setAccessControlList(acl);
            }
            // upload
            amazonS3.putObject(request);
        } catch (AmazonServiceException ase) {
            logger.error("Caught an AmazonServiceException, which means your request made it "
                    + "to Amazon S3, but was rejected with an error response for some reason.");
            logger.error("Error Message:    " + ase.getMessage());
            logger.error("HTTP Status Code: " + ase.getStatusCode());
            logger.error("AWS Error Code:   " + ase.getErrorCode());
            logger.error("Error Type:       " + ase.getErrorType());
            logger.error("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            logger.error("Caught an AmazonClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with S3, "
                    + "such as not being able to access the network.");
            logger.error("Error Message: " + ace.getMessage());
        }
        return key;
    }
    public String generateKeyByDate( String imgUse, String file_name) throws Exception {
        SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy");
        SimpleDateFormat dateFormatter2 = new SimpleDateFormat("MM");
        SimpleDateFormat dateFormatter3 = new SimpleDateFormat("dd");
        return imgUse + "/" + dateFormatter1.format(new Date()) + "/" + dateFormatter2.format(new Date()) + "/" + dateFormatter3.format(new Date()) + "/" + file_name;
    }
    public String generateKey(String imgUse, String file_name) throws Exception {
        return imgUse + "/" + file_name.substring(0, 2) + "/" + file_name;
    }
}
