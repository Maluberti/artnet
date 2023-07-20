package com.internship.project.artnet.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.*;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

// ...
@Configuration
public class AwsS3Config {

    private final String accessKey = "AKIAWMTLVN6LYC3D4IQB";
    private final String secretKey = "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";
    private final String bucketName = "images-artnet";

    @Bean
    public S3Client s3Client() {
        AwsCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(
                AwsBasicCredentials.create(accessKey, secretKey));

        return S3Client.builder()
                .region(Region.SA_EAST_1) // Escolha a região adequada para o seu bucket
                .credentialsProvider(credentialsProvider)
                .build();
    }

    public String getBucketName() {
        return bucketName;
    }
}

