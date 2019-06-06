package edu.mmc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.mmc.entity.Applycation;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.mmc.entity.vo.AifVo;
import edu.mmc.entity.vo.AuditVo;
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
public interface IApplycationService extends IService<Applycation> {
     List<AifVo> applyPage(Page page, String field, String fval);

     void dela(Integer id);

     AuditVo getAuditVo(Integer tid);
}