/**
 * Class Name: AttachmentServiceImpl
 * Description: Implementation for Attachment Service
 * 
 * Author: Dennis Wang & He Shen
 * Date: 2023/10/8
 */

package it.project.application.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import it.project.application.mapper.AttachmentMapper;
import it.project.application.pojo.Attachment;
import it.project.application.service.IAttachmentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttachmentServiceImpl extends ServiceImpl<AttachmentMapper, Attachment> implements IAttachmentService {
    @Autowired
    private AttachmentMapper attachmentMapper;

    @Override
    public List<Attachment> getByRequestId(Integer requestId) {
        QueryWrapper<Attachment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("requestId", requestId);
        return attachmentMapper.selectList(queryWrapper);
    }
}
