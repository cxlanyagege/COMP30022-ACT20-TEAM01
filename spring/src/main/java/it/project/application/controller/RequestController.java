package it.project.application.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import it.project.application.entity.Request;
import it.project.application.service.RequestService;
import it.project.application.util.JwtUtil;

@RestController
public class RequestController {
  
  @Autowired
  private RequestService requestService;

  @Autowired
    private JwtUtil jwtUtil;

  @PostMapping("/api/saveRequest")
  public ResponseEntity<Map<String, String>> saveRequest(
    @RequestHeader("Authorization") String authorizationHeader, 
    @RequestBody Request request) {
                              
    String jwt = authorizationHeader.substring(7);
    Long studentId = Long.parseLong(jwtUtil.extractStudentId(jwt));

    request.setStudentId(studentId);
    requestService.save(request);
    
    return ResponseEntity.ok(Map.of("message", "Request saved successfully!"));
  }

  @GetMapping("/api/getRequests")
  public List<Request> getRequests(@RequestHeader("Authorization") String authorizationHeader) {

    String jwt = authorizationHeader.substring(7);
    Long studentId = Long.parseLong(jwtUtil.extractStudentId(jwt));
    
    return requestService.getRequests(studentId);
  }
}
