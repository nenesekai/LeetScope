package usc.edu.csci201.leetscope.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@TableName("`user`")
@JsonIgnoreProperties(value = "password", allowSetters = true)
public class User {
    private Long id;
    private String name;
    private String password;
    private Boolean isTeacher;
    private Boolean isStudent;
}
