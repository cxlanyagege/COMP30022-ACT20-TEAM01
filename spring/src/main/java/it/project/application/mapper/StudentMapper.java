/**
 * Class Name: StudentMapper
 * Description: ORM Mapper for Student Entity
 * 
 * Author: He Shen
 * Date: 2023/9/23
 * 
 * Note: Find student entity by student id
 */

package it.project.application.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import it.project.application.entity.Student;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    @Select("SELECT * FROM student WHERE StudentId = #{id}")
    Student findById(Long id);
}
