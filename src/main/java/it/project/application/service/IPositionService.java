/**
 * Author: Dennis Wang & He Shen
 * Last modified data: 2023-11-14
 * Description: position service interface to be implemented
 */

package it.project.application.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import it.project.application.pojo.Position;

public interface IPositionService extends IService<Position> {
    public boolean checkExistence(Integer staffId, Integer subjectId);
    public List<Integer> getSubjectIdsByTutorId(Integer tutorId);
}
