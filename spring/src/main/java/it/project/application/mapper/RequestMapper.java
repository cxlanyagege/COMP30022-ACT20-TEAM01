package it.project.application.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import it.project.application.entity.Request;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RequestMapper extends BaseMapper<Request> {
}
