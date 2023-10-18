package com.example.it.project.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.it.project.mapper.AttachmentMapper;
import com.example.it.project.mapper.RequestMapper;
import com.example.it.project.pojo.Attachment;
import com.example.it.project.pojo.Request;
import com.example.it.project.service.IRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// 用到的mapper，操作的数据对象类型
public class RequestServiceImpl extends ServiceImpl<RequestMapper, Request> implements IRequestService {
    @Autowired
    private RequestMapper requestMapper;
    @Autowired
    private AttachmentMapper attachmentMapper;
    public void saveMain(Request request, List<Attachment> attachments){
        requestMapper.insert(request);

        if (attachments != null){
            for (Attachment attachment: attachments){
                attachment.setRequestId(request.getRequestId());
                attachmentMapper.insert(attachment);
            }
        }
    }
}
