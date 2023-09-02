package it.project.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import it.project.application.entity.Request;
import it.project.application.service.RequestService;

@RestController
public class RequestController {
  
  @Autowired
  private RequestService requestService;

  @PostMapping("/api/saveRequest")
  public String saveRequest(@RequestParam String description) {
    Request request = new Request();
    request.setDescription(description);
    requestService.save(request);
    return "Request saved successfully!";
  }
}
