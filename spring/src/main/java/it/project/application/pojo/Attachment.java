package it.project.application.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Attachment")
public class Attachment {

    @TableId("attachment_id") // primary key
    private Integer attachmentId;
    private String url;
    private Integer requestId;
}
