/**
 * Class Name: RequestService
 * Description: Service provided for manipulating requests
 * 
 * Author: He Shen
 * Date: 2023/8/16
 */

package it.project.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import it.project.application.entity.Request;
import it.project.application.mapper.RequestMapper;

@Service
public class RequestService {

  @Autowired
  private RequestMapper requestMapper;

  public void save(Request request) {
    requestMapper.insert(request);
  }

  public List<Request> getRequests(Long studentId) {
    QueryWrapper<Request> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("StudentID", studentId);
    return requestMapper.selectList(queryWrapper);
  }
}
