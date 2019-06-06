package edu.mmc.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.mmc.entity.Inspection;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.mmc.entity.vo.AifVo;
import edu.mmc.entity.vo.AuditVo;
import edu.mmc.entity.vo.InspVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2019-04-11
 */
public interface InspectionMapper extends BaseMapper<Inspection> {
     List<AifVo> inspPage(IPage page, @Param("field") String field, @Param("id") String fval);

     AuditVo getAuditVo(@Param("tid")Integer tid);

     InspVo getinspVo(@Param("tid") Integer tid);
}
