/**
 * Author: Dennis Wang
 * Last modified data: 2023-11-12
 * Description: position service do operations related to database
 */

package com.example.it.project.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.it.project.mapper.PositionMapper;
import com.example.it.project.pojo.Position;
import com.example.it.project.service.IPositionService;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {
}
