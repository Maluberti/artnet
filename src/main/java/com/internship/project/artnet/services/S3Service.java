package com.internship.project.artnet.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;

@Service
public class S3Service {
    @Autowired
    private final S3Client s3Client;
    @Autowired
    private final String bucketName; // Nome do bucket S3

    public S3Service(S3Client s3Client, String bucketName) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    // Construtor

    public String uploadImage(String nomeArquivo, InputStream inputStream, Long obraDeArteId) throws IOException {
        String caminhoS3 = "artwork/images" + obraDeArteId + "/" + nomeArquivo;
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(caminhoS3)
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(inputStream, inputStream.available()));
        return caminhoS3;
    }

    public InputStream downloadImage(String caminhoS3) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(bucketName)
                .key(caminhoS3)
                .build();

        return s3Client.getObject(getObjectRequest);
    }
}
