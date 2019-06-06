package edu.mmc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.mmc.entity.Inspection;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.mmc.entity.vo.AifVo;
import edu.mmc.entity.vo.AuditVo;
import edu.mmc.entity.vo.InspVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Huang
 * @since 2019-04-11
 */
public interface IInspectionService extends IService<Inspection> {
    List<AifVo>  inspPage(Page page, String field, String fval);

    AuditVo getAuditVo(Integer tid);

    InspVo getinspVo(Integer tid);
}
