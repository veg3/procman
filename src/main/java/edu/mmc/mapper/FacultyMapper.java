package edu.mmc.mapper;

import edu.mmc.entity.Faculty;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2019-04-24
 */
public interface FacultyMapper extends BaseMapper<Faculty> {
    public List<String> faNames();
}
