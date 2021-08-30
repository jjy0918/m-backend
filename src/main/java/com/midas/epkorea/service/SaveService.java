package com.midas.epkorea.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.midas.epkorea.dto.ResponseDto;
import com.midas.epkorea.exception.FileNameNotFoundException;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

@Service
@NoArgsConstructor
public class SaveService {
    private AmazonS3 s3Client;

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    private Random rnd = new Random();

    @PostConstruct
    public void setS3Client() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);

        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }




    private String upload(MultipartFile file) throws IOException, FileNameNotFoundException {
        Date time = new Date();
        String strFileName = file.getOriginalFilename();
        if(strFileName==null){
            throw new FileNameNotFoundException();
        }
        int pos = strFileName.lastIndexOf(".");
        String ext = strFileName.substring(pos + 1);
        String fileName = time.getTime() + randomToken() + "." + ext;

        s3Client.putObject(new PutObjectRequest(bucket, fileName, file.getInputStream(), null)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return s3Client.getUrl(bucket, fileName).toString();
    }



    private String randomToken() {
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int rIndex = rnd.nextInt(3);
            switch (rIndex) {
                case 0:
                    // a-z
                    token.append((char) ((rnd.nextInt(26)) + 97));
                    break;
                case 1:
                    // A-Z
                    token.append((char) ((rnd.nextInt(26)) + 65));
                    break;
                case 2:
                    // 0-9
                    token.append((rnd.nextInt(10)));
                    break;
                default:
                    break;
            }
        }
        return token.toString();
    }

    public ResponseEntity<ResponseDto> registerImageIntoServer(MultipartFile file) throws IOException, FileNameNotFoundException {
        String imagePath = upload(file);
        HashMap<String, String> hashMap = new LinkedHashMap<>();
        hashMap.put("image", imagePath);

        ResponseDto result = ResponseDto.builder()
                .message("image save in s3")
                .data(hashMap)
                .build();


        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
