/**
 * Author: Dennis Wang
 * Last modified data: 2023-10-19
 * Description: attachment service do operations related to database
 */

package com.example.it.project.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.it.project.mapper.AttachmentMapper;
import com.example.it.project.pojo.Attachment;
import com.example.it.project.service.IAttachmentService;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements IAttachmentService {
}
