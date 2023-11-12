package it.project.application.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("Staff_member")
public class Staff {
    @TableId("staff_id")
    private Integer staffId;
    private String name;
    private String role;
    private String email;
    private boolean newRequest;
    private boolean flaggedRequest;
}
