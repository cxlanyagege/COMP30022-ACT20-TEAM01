package it.project.application.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("request")
public class Request {
  @TableId(value = "id", type = IdType.AUTO)
  private Long id;

  private String description;

  // Getter and Setter
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
