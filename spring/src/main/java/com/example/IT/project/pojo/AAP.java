package com.example.it.project.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("AAP_attachment")
public class AAP {
    @TableId("aap_id")
    private Integer aapId;
    private String attachmentUrl;
    private Integer studentId;
}
