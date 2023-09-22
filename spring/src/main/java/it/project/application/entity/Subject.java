/**
 * Class Name: Subject
 * Description: Entity for subjects
 * 
 * Author: He Shen
 * Date: 2023/9/23
 */

package it.project.application.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("subject")
public class Subject {

    @TableId
    private Integer id;

}
