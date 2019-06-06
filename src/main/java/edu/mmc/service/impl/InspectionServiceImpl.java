package edu.mmc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.mmc.entity.Inspection;
import edu.mmc.entity.vo.AifVo;
import edu.mmc.entity.vo.AuditVo;
import edu.mmc.entity.vo.InspVo;
import edu.mmc.mapper.InspectionMapper;
import edu.mmc.service.IInspectionService;
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
 * @since 2019-04-11
 */
@Service
public class InspectionServiceImpl extends ServiceImpl<InspectionMapper, Inspection> implements IInspectionService {
    @Autowired
    private InspectionMapper ism;
    @Override
    public List<AifVo> inspPage(Page page, String field, String fval) {
        return ism.inspPage(page,field,fval);
    }

    @Override
    public AuditVo getAuditVo(Integer tid) {
        return ism.getAuditVo(tid);
    }

    @Override
    public InspVo getinspVo(Integer tid) {
        return ism.getinspVo(tid);
    }
}
