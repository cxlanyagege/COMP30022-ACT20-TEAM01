/**
 * Class Name: SubjectMapper
 * Description: ORM Mapper for Subject Entity
 * 
 * Author: He Shen
 * Date: 2023/10/9
 * 
 * Note: Find subject entity by subject id
 */

package it.project.application.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import it.project.application.entity.Subject;

@Mapper
public interface SubjectMapper extends BaseMapper<Subject> {
    @Select("SELECT * FROM subject WHERE subject_id = #{id}")
    Subject findById(Long id);
}
