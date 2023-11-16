package nenesekai.leetscope.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("`assignment`")
public class Assignment {
    private Long id;
    private Long uid;
    private String title;
    private String description;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    private Date deadline;
    private int allowedAttempts;
}
