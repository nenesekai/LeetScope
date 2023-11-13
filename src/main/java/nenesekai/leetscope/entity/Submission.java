package nenesekai.leetscope.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("`submission`")
public class Submission {
    private Long id;
    private Long uid;
    private Long assignmentId;
    private String fileName;
    private Boolean isGraded;
    private Boolean isPass;
    private Date createTime;
}
