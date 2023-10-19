/**
 * Author: Dennis Wang
 * Last modified data: 2023-10-19
 * Description: storage service interface
 */

package com.example.it.project.service;


import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
    Boolean save(MultipartFile file, String fileName, String filePath);
}
