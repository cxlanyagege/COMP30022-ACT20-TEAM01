/**
 * Class Name: SubjectServiceImpl
 * Description: Subject service do operations related to database
 * 
 * Author: He Shen & Dennis Wang
 * Date: 2023/11/12
 */

package it.project.application.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import it.project.application.mapper.SubjectMapper;
import it.project.application.pojo.Subject;
import it.project.application.service.ISubjectService;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements ISubjectService {
}
