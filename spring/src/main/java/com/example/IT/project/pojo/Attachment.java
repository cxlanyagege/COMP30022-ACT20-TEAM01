/**
 * Author: Dennis Wang
 * Last modified data: 2023-10-19
 * Description: use to store info related to attachment that will be stored in database as well
 */

package com.example.it.project.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("Attachment")
public class Attachment {
    // related to database
    @TableId("attachment_id") // primary key
    private Integer attachmentId;
    private String url;
    private Integer requestId;
}
