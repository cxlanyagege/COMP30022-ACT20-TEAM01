/**
 * Class Name: StorageServiceTest
 * Description: Test for attachment storage service
 * 
 * Author: He Shen
 * Date: 2023/10/19
 */

package it.project.application.service;

import it.project.application.service.Impl.StorageServiceImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

@SpringBootTest
class StorageServiceTest {

    private StorageServiceImpl storageService;

    @TempDir
    File tempDir;

    @BeforeEach
    public void setup() {
        storageService = new StorageServiceImpl();
    }

    @Test
    void testSave() throws IOException {
        // Arrange
        String originalFileName = "test.pdf";
        String contentType = "text/plain";
        byte[] content = "Storage Test".getBytes();

        // Act
        MultipartFile multipartFile = new MockMultipartFile("file", originalFileName, contentType, content);

        String fileName = "newFile.pdf";
        String filePath = tempDir.getAbsolutePath() + File.separator;

        Boolean result = storageService.save(multipartFile, fileName, filePath);

        // Assert
        assertTrue(result, "File saved successfully");
    }
}
