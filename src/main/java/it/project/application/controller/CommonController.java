/**
 * Class Name: CommonController
 * Description: Controller for handling uploading attachments
 * 
 * Author: Dennis Wang & He Shen
 * Date: 2023/10/19
 */

package it.project.application.controller;

import it.project.application.service.IStorageService;
import it.project.application.vo.Result;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/")
public class CommonController {

    @Autowired
    private IStorageService storageService;

    @Value("${upload.accessPath}")
    private String accessPath;

    @Value("${upload.localPath}")
    private String localPath;

    // Handle upload and store in local
    @PostMapping("/upload")
    public Result upload(HttpServletRequest request, HttpServletResponse response){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("file");
        String filename = file.getOriginalFilename();
        log.info("Upload filename={}", filename);
        storageService.save(file, filename, localPath);
        return Result.success("Upload successfully", accessPath + filename);
    }
}
