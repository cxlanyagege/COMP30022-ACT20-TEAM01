/**
 * Class Name: RequestMapper
 * Description: ORM Mapper for Request Entity
 * 
 * Author: He Shen
 * Date: 2023/8/16
 */

package it.project.application.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import it.project.application.entity.Request;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RequestMapper extends BaseMapper<Request> {
}
