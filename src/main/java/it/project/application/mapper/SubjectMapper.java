/**
 * Class Name: SubjectMapper
 * Description: Subject mapper created to connect to JDBC automatically from relevant service
 * 
 * Author: He Shen & Dennis Wang
 * Date: 2023/11/12
 * 
 * Note: Find subject entity by subject id
 */

package it.project.application.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import it.project.application.pojo.Subject;

@Mapper
public interface SubjectMapper extends BaseMapper<Subject> {
}
