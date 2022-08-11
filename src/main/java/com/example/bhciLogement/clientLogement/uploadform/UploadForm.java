package com.example.bhciLogement.clientLogement.uploadform;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UploadForm {
    private String description;
    private MultipartFile[] files;
}
