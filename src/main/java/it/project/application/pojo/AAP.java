/**
 * Author: Dennis Wang
 * Last modified data: 2023-11-12
 * Description: use to store info related to AAP that will be stored in database
 */

package it.project.application.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("AAP_attachment")
public class AAP {
    @TableId("aap_id") // specify the primary key
    private Integer aapId;
    private String attachmentUrl;
    private Integer studentId;
}
