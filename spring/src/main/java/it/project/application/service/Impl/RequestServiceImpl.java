/**
 * Class Name: RequestServiceImpl
 * Description: Implementation for Request Service
 * 
 * Author: Dennis Wang
 * Date: 2023/9/23
 */

package it.project.application.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import it.project.application.mapper.AttachmentMapper;
import it.project.application.mapper.RequestMapper;
import it.project.application.pojo.Attachment;
import it.project.application.pojo.Request;
import it.project.application.service.IRequestService;

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
