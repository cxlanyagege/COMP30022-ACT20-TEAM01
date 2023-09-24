/**
 * Class Name: IStorageService
 * Description: Interface for Storage Service
 * 
 * Author: Dennis Wang
 * Date: 2023/9/23
 */

package it.project.application.service;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
    Boolean save(MultipartFile file, String fileName, String filePath);
}
