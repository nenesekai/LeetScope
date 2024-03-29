package nenesekai.leetscope.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import nenesekai.leetscope.entity.User;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where name = '${username}'")
    List<User> listUserByName(String username);
}