/**
 * Class Name: StudentMapper
 * Description: Student mapper created to connect to JDBC automatically from relevant service
 * 
 * Author: He Shen & Dennis Wang
 * Date: 2023/11/12
 * 
 * Note: Find student entity by student id
 */

package it.project.application.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import it.project.application.pojo.Student;

@Mapper
public interface StudentMapper extends BaseMapper<Student> {
}
