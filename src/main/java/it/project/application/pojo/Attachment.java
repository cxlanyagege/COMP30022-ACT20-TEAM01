/**
 * Class Name: Attachment
 * 
 * Description: Use to store info related to attachment 
 *              that will be stored in database as well
 * 
 * Author: Dennis Wang & He Shen
 * 
 * Date: 2023/10/20
 */

package it.project.application.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("Attachment")
public class Attachment {
    // related to database
    @TableId("attachment_id") // primary key
    private Integer attachmentId;
    private String url;
    private Integer requestId;

}
