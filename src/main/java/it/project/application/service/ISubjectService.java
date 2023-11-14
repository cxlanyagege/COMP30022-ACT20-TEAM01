/**
 * Class Name: ISubjectService
 * Description: Subject service interface to be implemented
 * 
 * Author: He Shen & Dennis Wang
 * Date: 2023/11/14
 */

package it.project.application.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import it.project.application.pojo.Subject;

public interface ISubjectService extends IService<Subject> {
    List<Subject> listSubjectsByIds(List<Integer> subjectIds);
}
