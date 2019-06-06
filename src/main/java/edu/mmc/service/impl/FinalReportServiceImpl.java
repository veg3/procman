package edu.mmc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.mmc.entity.FinalReport;
import edu.mmc.entity.vo.AifVo;
import edu.mmc.entity.vo.AuditVo;
import edu.mmc.entity.vo.FinalVo;
import edu.mmc.mapper.FinalReportMapper;
import edu.mmc.service.IFinalReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Huang
 * @since 2019-04-26
 */
@Service
public class FinalReportServiceImpl extends ServiceImpl<FinalReportMapper, FinalReport> implements IFinalReportService {
    @Autowired
    private FinalReportMapper frm;
    @Override
    public List<AifVo> fnrpPage(Page page, String field, String fval) {
        return frm.fnrpPage(page,field,fval);
    }

    @Override
    public AuditVo getAuditVo(Integer tid) {
        return frm.getAuditVo(tid);
    }

    @Override
    public FinalVo getFinalVo(Integer tid) {
        return frm.getFinalVo(tid);
    }

    @Override
    public AuditVo getcomm(Integer tid) {
        return frm.getcomm(tid);
    }
}
