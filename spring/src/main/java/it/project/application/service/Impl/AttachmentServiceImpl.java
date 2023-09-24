/**
 * Class Name: AttachmentServiceImpl
 * Description: Implementation for Attachment Service
 * 
 * Author: Dennis Wang
 * Date: 2023/9/23
 */

package it.project.application.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import it.project.application.mapper.AttachmentMapper;
import it.project.application.pojo.Attachment;
import it.project.application.service.IAttachmentService;

import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements IAttachmentService {
}
