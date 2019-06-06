package edu.mmc.mapper;

import edu.mmc.entity.Major;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2019-04-24
 */
public interface MajorMapper extends BaseMapper<Major> {
    public List<String> mjNames(@Param("faN") String faN);
}
