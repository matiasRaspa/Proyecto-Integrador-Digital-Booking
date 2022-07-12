package com.proyectointegrador.proyecto_Integrador_CTD.service.imp.aws;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.File;

@Getter
@Setter
@Component
public class AWSS3Service {
    private final static Logger logger = Logger.getLogger(AWSS3Service.class);

    private final AmazonS3 s3client = AmazonS3ClientBuilder
            .standard()
            .withCredentials(new AWSStaticCredentialsProvider(credentials))
            .withRegion(Regions.US_EAST_2)
            .build();
    ;
    private static final AWSCredentials credentials;

    static {
        credentials = new BasicAWSCredentials(
                "AKIASDYGBXDRVW5ARYAU",
                "BCZKViqpPogP4Ygz+SwTPym5JM3itTcOBRoRzgOk"
        );
    }

    //upload  object
    public String putObject(String bucketName, String key, File file) {
        logger.info(this.getS3client());
        try {
            logger.info("Uploading object to S3" + bucketName + key + file);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file)
                    .withCannedAcl(CannedAccessControlList.PublicRead);
            s3client.putObject(putObjectRequest);
            logger.info("Uploaded object to S3" );
            //delete the local file
            file.delete();
        } catch (Exception e) {
            logger.error(e);
        }
        return "https://"+bucketName+".s3.us-east-2.amazonaws.com/"+key;
    }


/*
    //list objects
    public ObjectListing listObjects(String bucketName) {
        return s3client.listObjects(bucketName);
    }

    //get an object
    public S3Object getObject(String bucketName, String objectKey) {
        return s3client.getObject(bucketName, objectKey);
    }


    //deleting an object
    public void deleteObject(String bucketName, String objectKey) {
        s3client.deleteObject(bucketName, objectKey);
    }

    //deleting multiple Objects
    public DeleteObjectsResult deleteObjects(DeleteObjectsRequest delObjReq) {
        return s3client.deleteObjects(delObjReq);
    }*/

    public AWSS3Service() {
    }
}

