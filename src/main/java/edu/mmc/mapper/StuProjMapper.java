package edu.mmc.mapper;

import edu.mmc.entity.StuProj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.mmc.entity.vo.StuProjVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2019-04-17
 */
public interface StuProjMapper extends BaseMapper<StuProj> {
      List<StuProjVo> stup(Integer tid);

      List<StuProjVo> leader(@Param("tid") Integer tid);

      List<StuProjVo> otherspj(@Param("tid") Integer tid);

      Boolean del (@Param("tid") Integer tid,@Param("uid")Long uid);

      Boolean save(@Param("tid")Integer tid,@Param("uid")Long uid);
}
