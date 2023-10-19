/**
 * Class Name: RequestMapper
 * 
 * Description: Request mapper created to connect to 
 *              JDBC automatically from relevant service
 * 
 * Author: Dennis Wang
 * 
 * Date: 2023/10/20
 */

package it.project.application.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import it.project.application.pojo.Request;

public interface RequestMapper extends BaseMapper<Request> {
}
