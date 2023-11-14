/**
 * Author: Dennis Wang & He Shen
 * Last modified data: 2023-11-14
 * Description: Position mapper created to connect to JDBC automatically from relevant service
 */

package it.project.application.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import it.project.application.pojo.Position;

public interface PositionMapper extends BaseMapper<Position> {
    @Select("SELECT COUNT(*) FROM is_responsible_for WHERE staff_id = #{staffId} AND subject_id = #{subjectId}")
    int existsByStaffIdAndSubjectId(@Param("staffId") Integer staffId, @Param("subjectId") Integer subjectId);

    @Select("SELECT subject_id FROM is_responsible_for WHERE staff_id = #{tutorId}")
    List<Integer> findSubjectIdsByTutorId(@Param("tutorId") Integer tutorId);
}
