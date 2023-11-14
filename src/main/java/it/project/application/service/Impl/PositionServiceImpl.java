/**
 * Author: Dennis Wang & He Shen
 * Last modified data: 2023-11-14
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
    public boolean checkExistence(Integer staffId, Integer subjectId) {
        // Use methods defined in mapper
        // Return matching record counts
        int count = this.baseMapper.existsByStaffIdAndSubjectId(staffId, subjectId);
        return count > 0;
    }
}
