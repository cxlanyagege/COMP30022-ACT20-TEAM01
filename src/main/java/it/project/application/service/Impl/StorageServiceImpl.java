/**
 * Class Name: StorageServiceImpl
 * 
 * Description: Store files locally
 * 
 * Author: Dennis Wang
 * 
 * Date: 2023/10/20
 */

package it.project.application.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import it.project.application.service.IStorageService;

import java.io.File;
import java.io.IOException;

@Service
public class StorageServiceImpl implements IStorageService {

    @Override
    public Boolean save(MultipartFile file, String fileName, String filePath) {
        String path = filePath + fileName;
        File targetFile = new File(path);
        // if no exists folder
        if (!targetFile.getParentFile().exists()){
            targetFile.getParentFile().mkdirs();
        }

        try {
            FileCopyUtils.copy(file.getBytes(), targetFile);
            return true;
        } catch (IOException e) {
        }
        return false;
    }
}
