package edu.mmc.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.mmc.entity.FinalReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.mmc.entity.vo.AifVo;
import edu.mmc.entity.vo.AuditVo;
import edu.mmc.entity.vo.FinalVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Huang
 * @since 2019-04-26
 */
public interface FinalReportMapper extends BaseMapper<FinalReport> {
     List<AifVo> fnrpPage(IPage page, @Param("field") String field, @Param("id") String fval);

     AuditVo getAuditVo(@Param("tid")Integer tid);

     FinalVo getFinalVo(@Param("tid")Integer tid);

     AuditVo getcomm(@Param("tid")Integer tid);
}
