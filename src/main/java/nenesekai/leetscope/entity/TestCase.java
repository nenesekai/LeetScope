package nenesekai.leetscope.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("`test_case`")
public class TestCase {
    private Long id;
    private Long assignmentId;
    private String inputFileName;
    private String outputFileName;
}
