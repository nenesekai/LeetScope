package nenesekai.leetscope.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("`submission`")
public class Submission {
    private Integer id;
    private Integer uid;
    private Integer assignmentId;
    private String fileName;
    private Boolean isGraded;
    private Boolean isPass;
    private Date createTime;
}
