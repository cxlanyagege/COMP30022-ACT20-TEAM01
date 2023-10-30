/**
 * Author: Dennis Wang
 * Last modified data: 2023-10-21
 * Description: unit test on uploading file and successfully saved locally through common controller
 */

package it.project.application.controller;

import it.project.application.vo.Result;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class CommonControllerTest extends AbstractTest{
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void uploadFile() throws Exception{
        String uri = "/upload";

        String filePath = "/Users/wangzeyu/Desktop/test_file1.png";

        // Read the file content into a byte array
        Path path = Paths.get(filePath);
        byte[] fileContent = Files.readAllBytes(path);

        MockMultipartFile mockFile = new MockMultipartFile("file", "test_file1.png",
                MediaType.IMAGE_PNG_VALUE, fileContent);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.multipart(uri)
                .file(mockFile)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        Result<String> result = super.mapFromJson(content, new TypeReference<>() {});
        assertEquals(0, result.getCode());
        assertFalse(result.getData().isEmpty());
        assertEquals(result.getMsg(), "Upload successfully");
    }
}
