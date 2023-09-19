/**
 * Class Name: StudentMapper
 * Description: ORM Mapper for Student Entity
 * 
 * Author: He Shen
 * Date: 2023/8/16
 */

package it.project.application.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import it.project.application.entity.Student;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
    @Select("SELECT * FROM student WHERE Name = #{username}")
    Student findByName(String username);
}
