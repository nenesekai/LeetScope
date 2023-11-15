package nenesekai.leetscope.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nenesekai.leetscope.entity.Assignment;
import nenesekai.leetscope.entity.TestCase;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestCaseMapper extends BaseMapper<TestCase> {
}
