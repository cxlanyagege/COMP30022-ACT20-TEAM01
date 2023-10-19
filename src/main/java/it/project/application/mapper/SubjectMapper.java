/**
 * Class Name: SubjectMapper
 * Description: ORM Mapper for Subject Entity
 * 
 * Author: He Shen & Dennis Wang
 * Date: 2023/10/19
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
