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

  // Getter and Setter
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setStudentId(Long studentId) {
    this.studentId = studentId;
  }
}
