package nenesekai.leetscope.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import nenesekai.leetscope.entity.Submission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SubmissionMapper extends BaseMapper<Submission> {
    @Select("select * from submission s where s.uid = ${uid} and s.assignment_id = ${assignmentId}")
    public List<Submission> listSubmissions(Integer uid, Integer assignmentId);

    @Delete("delete from submission s where s.assignment_id = ${assignmentId}")
    public void deleteSubmissionByAssignmentId(Integer assignmentId);
}
