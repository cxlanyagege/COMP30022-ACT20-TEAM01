package com.example.IT.project.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.IT.project.mapper.AttachmentMapper;
import com.example.IT.project.pojo.Attachment;
import com.example.IT.project.service.IAttachmentService;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements IAttachmentService {
}
