package com.internship.project.artnet;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonS3Config {
    @Value("${accessKey}")
    private  String accessKey;

    @Value("${secretKey}")
    private  String secretKey;

    @Value("${region}")
    private  String region;

    @Value("${bucketName}")
    private  String bucketName;

    @Bean
    public AmazonS3 amazonS3(){
        AWSCredentials credentials = new BasicAWSCredentials(accessKey, secretKey);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials( new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        return s3Client;
    }
    @Bean
    public String getBucketName(){
        return bucketName;
    }
}