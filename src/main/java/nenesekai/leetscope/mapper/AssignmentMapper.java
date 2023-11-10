package nenesekai.leetscope.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nenesekai.leetscope.entity.Assignment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Qixuan Chen
 */
@Mapper
public interface AssignmentMapper extends BaseMapper<Assignment> {
    @Select("select * from assignment")
    public List<Assignment> listAllAssignments();
}
