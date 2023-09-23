package com.example.IT.project.service;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
    Boolean save(MultipartFile file, String fileName, String filePath);
}
