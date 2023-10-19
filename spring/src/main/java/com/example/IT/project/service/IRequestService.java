/**
 * Author: Dennis Wang
 * Last modified data: 2023-10-19
 * Description: request service interface with an extra saveMain waiting to be implemented
 */

package com.example.it.project.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.it.project.pojo.Attachment;
import com.example.it.project.pojo.Request;

import java.util.List;

public interface IRequestService extends IService<Request> {
    void saveMain(Request request, List<Attachment> attachments);
}
