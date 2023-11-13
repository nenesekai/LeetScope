package nenesekai.leetscope.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nenesekai.leetscope.entity.Assignment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubmissionMapper extends BaseMapper<Assignment> {

}
