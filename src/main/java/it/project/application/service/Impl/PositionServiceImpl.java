/**
 * Author: Dennis Wang
 * Last modified data: 2023-11-12
 * Description: position service do operations related to database
 */

package it.project.application.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import it.project.application.mapper.PositionMapper;
import it.project.application.pojo.Position;
import it.project.application.service.IPositionService;
import org.springframework.stereotype.Service;

@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, Position> implements IPositionService {
}
