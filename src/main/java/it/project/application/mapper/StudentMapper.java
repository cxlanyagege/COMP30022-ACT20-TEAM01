/**
 * Class Name: StudentMapper
 * Description: ORM Mapper for Student Entity
 * 
 * Author: He Shen & Dennis Wang
 * Date: 2023/10/19
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
