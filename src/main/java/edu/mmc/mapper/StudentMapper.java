package edu.mmc.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.mmc.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface StudentMapper extends BaseMapper<Student> {
     List<Student> aslist(IPage page,@Param("faculty") String faculty, @Param("major") String major
                            , @Param("name")String name);

     Boolean updByoid(@Param("stu")Student stu,@Param("oid") Long oid);

     String fname(@Param("id")Long id);
}
