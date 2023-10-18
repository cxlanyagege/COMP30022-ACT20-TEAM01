/**
 * Class Name: IRequestService
 * Description: Interface for Request Service
 * 
 * Author: Dennis Wang
 * Date: 2023/9/23
 */

package it.project.application.service;

import com.baomidou.mybatisplus.extension.service.IService;

import it.project.application.pojo.Attachment;
import it.project.application.pojo.Request;

import java.util.List;

public interface IRequestService extends IService<Request> {

    void saveMain(Request request, List<Attachment> attachments);
}
