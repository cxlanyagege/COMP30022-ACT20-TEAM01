/**
 * Class Name: SubjectServiceImpl
 * Description: Implementation for Subject Service
 * 
 * Author: He Shen & Dennis Wang
 * Date: 2023/10/19
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
