/**
 * Class Name: Request
 * Description: Entity for requests submitting by student
 * 
 * Author: He Shen
 * Date: 2023/8/16
 * 
 * Note: Not finalized to fulfill every forms
 */

package it.project.application.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("request")
public class Request {

  @TableId(value = "requestId", type = IdType.AUTO)
  private Long requestId;
  private String description;
  private Long studentId;

  public String getDescription() {
    return description;
  }

  public Long getStudentId() {
    return studentId;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setStudentId(Long studentId) {
    this.studentId = studentId;
  }
}
