/**
 * Class Name: IAttachmentService
 * Description: Interface for Attachment Service
 * 
 * Author: Dennis Wang & He Shen
 * Date: 2023/10/8
 */

package it.project.application.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import it.project.application.pojo.Attachment;

public interface IAttachmentService extends IService<Attachment> {
    List<Attachment> getByRequestId(Integer requestId);
}
