package edu.mmc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.mmc.entity.FinalReport;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.mmc.entity.vo.AifVo;
import edu.mmc.entity.vo.AuditVo;
import edu.mmc.entity.vo.FinalVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Huang
 * @since 2019-04-26
 */
public interface IFinalReportService extends IService<FinalReport> {
     List<AifVo> fnrpPage(Page page, String field, String fval);

     AuditVo getAuditVo(Integer tid);

     FinalVo getFinalVo(Integer tid);

     AuditVo getcomm(Integer tid);
}
