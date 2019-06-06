package edu.mmc.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.mmc.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.mmc.entity.vo.TeacherVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2019-04-12
 */
public interface TeacherMapper extends BaseMapper<Teacher> {
     List<TeacherVo> getTup(@Param("name") String name);

     List<TeacherVo> atlist(IPage page,@Param("faculty")String faculty, @Param("name") String name);

     Boolean updByoid(@Param("tec") TeacherVo tec,@Param("oid") Long oid);
}
