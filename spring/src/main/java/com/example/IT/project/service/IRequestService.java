package com.example.IT.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.IT.project.pojo.Attachment;
import com.example.IT.project.pojo.Request;

import java.util.List;

public interface IRequestService extends IService<Request> {

    void saveMain(Request request, List<Attachment> attachments);
}
