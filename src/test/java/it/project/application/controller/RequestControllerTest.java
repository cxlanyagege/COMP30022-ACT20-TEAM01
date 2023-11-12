/**
 * Author: Dennis Wang & He Shen
 * Last modified data: 2023-11-10
 * Description: unit tests on request controller, delete, add, change and detail
 */

package it.project.application.controller;

import it.project.application.form.RequestForm;
import it.project.application.pojo.Attachment;
import it.project.application.pojo.Request;
import it.project.application.vo.RequestVo;
import it.project.application.vo.Result;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public class RequestControllerTest extends AbstractTest{
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    @Test
    public void getRequestsList() throws Exception {
        int studentId = 1266288;
        String uri = "/api/getRequests/" + studentId;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        Result<RequestVo[]> result = super.mapFromJson(content, new TypeReference<>() {});
        assertEquals(0, result.getCode());

        RequestVo[] requestList = result.getData();
        assertNotNull(requestList);
        assertTrue(requestList.length > 0);
    }

    @Test
    public void saveRequest() throws Exception {
        String uri = "/api/saveRequest";
        RequestForm request = new RequestForm();
        request.setStudentId(1266288);
        request.setSubjectId("COMP10012");
        request.setSubmissionDate(Date.valueOf("2023-10-20"));
        request.setRequestName("Testing request");
        request.setRequestType("Exam");
        request.setDescription("Testing description");
        request.setTaskType("Individual");
        request.setWorkType("Remark");
        List<Attachment> attachments = new ArrayList<>();
        attachments.add(new Attachment(null, "test1.png", null));
        attachments.add(new Attachment(null, "test2.png", null));
        request.setAttachments(attachments);
        request.setTeammates(new ArrayList<>());

        String inputJson = super.mapToJson(request);
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        Result<RequestVo> result = super.mapFromJson(content, new TypeReference<>() {});
        assertEquals(0, result.getCode());
        assertNotNull(result.getData());
        assertEquals(result.getMsg(), "Request submitted successfully!");
    }

    @Test
    public void deleteRequest() throws Exception {
        // Save a specified request given request id
        RequestForm request = new RequestForm();
        request.setStudentId(1266288);
        request.setSubjectId("COMP10012");
        request.setSubmissionDate(Date.valueOf("2023-10-20"));
        request.setRequestId(-1535074302);
        request.setRequestName("Testing request");
        request.setRequestType("Exam");
        request.setDescription("Testing description");
        request.setTaskType("Individual");
        request.setWorkType("Remark");
        List<Attachment> attachments = new ArrayList<>();
        attachments.add(new Attachment(null, "test1.png", null));
        attachments.add(new Attachment(null, "test2.png", null));
        request.setAttachments(attachments);
        request.setTeammates(new ArrayList<>());
        String inputJson = super.mapToJson(request);
        MvcResult saveResult = mvc.perform(MockMvcRequestBuilders.post("/api/saveRequest")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(inputJson)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int saveStatus = saveResult.getResponse().getStatus();
        assertEquals(200, saveStatus);

        // Once save succeed, then test deleting
        int requestId = -1535074302; // if further testing, need to change the requestId here
        String uri = "/api/deleteRequest/" + requestId;
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();
        Result result = super.mapFromJson(content, new TypeReference<>() {});
        assertEquals(0, result.getCode());
        assertEquals(result.getMsg(),"Successfully deleted");
    }

    @Test
    public void updateRequest() throws Exception {
        int requestId = 1553088514; // the request has already been updated, can change to different requestId
        String uri = "/api/updateRequest/" + requestId;
        String requestStatus = "APPROVED";
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
                .param("status", requestStatus)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        String content = mvcResult.getResponse().getContentAsString();

        Result<Request> result = super.mapFromJson(content, new TypeReference<>() {});
        assertEquals(0, result.getCode());
        assertNotNull(result.getData());
        assertEquals(requestStatus, result.getData().getStatus());
    }
}
