package it.project.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.project.application.entity.Request;
import it.project.application.mapper.RequestMapper;

@Service
public class RequestService {

  @Autowired
  private RequestMapper requestMapper;

  public void save(Request request) {
    requestMapper.insert(request);
  }
}
