/**
 * Class Name: IRequestService
 * 
 * Description: Request service interface with an extra 
 *              saveMain waiting to be implemented
 * 
 * Author: Dennis Wang
 * 
 * Date: 2023/10/20
 */

package it.project.application.service;

import com.baomidou.mybatisplus.extension.service.IService;

import it.project.application.pojo.Attachment;
import it.project.application.pojo.Request;

import java.util.List;

public interface IRequestService extends IService<Request> {

    void saveMain(Request request, List<Attachment> attachments);
}
