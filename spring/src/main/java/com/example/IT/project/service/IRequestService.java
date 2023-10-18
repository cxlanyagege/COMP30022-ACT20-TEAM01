package com.example.it.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.it.project.pojo.Attachment;
import com.example.it.project.pojo.Request;

import java.util.List;

public interface IRequestService extends IService<Request> {
    void saveMain(Request request, List<Attachment> attachments);
}
