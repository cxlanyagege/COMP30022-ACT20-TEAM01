/**
 * Author: Dennis Wang
 * Last modified data: 2023-11-12
 * Description: subject service do operations related to database
 */

package com.example.it.project.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.it.project.mapper.SubjectMapper;
import com.example.it.project.pojo.Subject;
import com.example.it.project.service.ISubjectService;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements ISubjectService {
}
