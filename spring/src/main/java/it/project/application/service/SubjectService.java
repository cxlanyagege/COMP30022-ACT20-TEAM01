/**
 * Class Name: SubjectService
 * Description: Service provided for manipulating subject
 * 
 * Author: He Shen
 * Date: 2023/9/23
 */

package it.project.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.project.application.entity.Subject;
import it.project.application.mapper.SubjectMapper;

@Service
public class SubjectService {

    @Autowired
    private SubjectMapper subjectMapper;

    // Store subject info into database
    public void store(Long id, String name) {

        // Try to find this subject in database
        Subject subject = subjectMapper.findById(id);

        // Record this new subject
        if (subject == null) {
            Subject newSubject = new Subject(id, name);
            subjectMapper.insert(newSubject);
        }
    }

    // Get subject info from subject id
    public Subject getSubjectInfo(Long subjectId) {
        return subjectMapper.selectById(subjectId);
    }
    
}
