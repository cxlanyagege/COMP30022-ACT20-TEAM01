/**
 * Class Name: IStorageService
 * 
 * Description: Storage service interface
 * 
 * Author: Dennis Wang
 * 
 * Date: 2023/10/20
 */

package it.project.application.service;

import org.springframework.web.multipart.MultipartFile;

public interface IStorageService {
    Boolean save(MultipartFile file, String fileName, String filePath);
}
