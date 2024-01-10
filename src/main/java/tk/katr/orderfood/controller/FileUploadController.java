package tk.katr.orderfood.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tk.katr.orderfood.config.CustomAuthorization;
import tk.katr.orderfood.domain.ApiResponse;

import java.io.File;
import java.io.IOException;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
public class FileUploadController {

    @Value("${file.upload.path}")
    private String filepath;

    @PostMapping("/api/upload")

    public ApiResponse<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException, NoSuchAlgorithmException {
        String originalFilename = file.getOriginalFilename();
        String fileExtension = StringUtils.getFilenameExtension(originalFilename);
        String md5Filename = generateMD5Filename(file.getBytes()) + "." + fileExtension;

        file.transferTo(new File(filepath + md5Filename));
        return ApiResponse.success(md5Filename);
    }

    private String generateMD5Filename(byte[] fileContent) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(fileContent);

        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
}