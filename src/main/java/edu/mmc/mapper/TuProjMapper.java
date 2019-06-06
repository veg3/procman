package edu.mmc.mapper;

import edu.mmc.entity.StuProj;
import edu.mmc.entity.TuProj;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.mmc.entity.vo.TuProjVo;
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
public interface TuProjMapper extends BaseMapper<TuProj> {
    String tusNames(@Param("tid") Integer tid);

    List<TuProjVo> tupj(@Param("tid") Integer tid);

    List<TuProjVo> othertuj(@Param("tid")Integer tid);

    Boolean del(@Param("uid")Long uid,@Param("tid")Integer tid);

    List<TuProjVo> leader(@Param("tid")Integer tid);

    Boolean save(@Param("tid")Integer tid,@Param("uid")Long uid);
}
