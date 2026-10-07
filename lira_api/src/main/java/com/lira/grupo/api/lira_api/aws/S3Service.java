package com.lira.grupo.api.lira_api.aws;

import com.lira.grupo.api.lira_api.exception.StorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

@Service
public class S3Service {

    private final S3Client s3Client;
    private final String bucketName;

    public S3Service(
            S3Client s3Client,
            @Value("${aws.s3.bucket-name:}") String bucketName
    ) {
        this.s3Client = s3Client;
        this.bucketName = bucketName;
    }

    public String uploadFile(String key, MultipartFile file) {
        validarBucketConfigurado();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(file.getContentType())
                .build();

        try {
            s3Client.putObject(
                    request,
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize())
            );
            return "Arquivo enviado: " + key;

        } catch (IOException exception) {
            throw new StorageException("Não foi possível ler o arquivo enviado.", exception);
        } catch (RuntimeException exception) {
            throw new StorageException("Não foi possível enviar o arquivo ao S3.", exception);
        }
    }

    public byte[] downloadFile(String key) {
        validarBucketConfigurado();

        try {
            GetObjectRequest request = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .build();

            return s3Client.getObjectAsBytes(request).asByteArray();

        } catch (RuntimeException exception) {
            throw new StorageException("Não foi possível baixar o arquivo do S3.", exception);
        }
    }

    private void validarBucketConfigurado() {
        if (bucketName == null || bucketName.isBlank()) {
            throw new StorageException("AWS S3 não configurado. Defina AWS_S3_BUCKET.");
        }
    }
}
