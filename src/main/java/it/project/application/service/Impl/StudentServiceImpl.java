/**
 * Class Name: StudentServiceImpl
 * Description: Student service do operations related to database
 * 
 * Author: He Shen & Dennis Wang
 * Date: 2023/11/12
 */

package it.project.application.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import it.project.application.mapper.StudentMapper;
import it.project.application.pojo.Student;
import it.project.application.service.IStudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {
}
