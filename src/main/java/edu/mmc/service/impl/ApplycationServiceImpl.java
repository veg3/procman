package edu.mmc.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.mmc.entity.Applycation;
import edu.mmc.entity.vo.AifVo;
import edu.mmc.entity.vo.AuditVo;
import edu.mmc.mapper.ApplycationMapper;
import edu.mmc.mapper.StuProjMapper;
import edu.mmc.mapper.TuProjMapper;
import edu.mmc.service.IApplycationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
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
public class ApplycationServiceImpl extends ServiceImpl<ApplycationMapper, Applycation> implements IApplycationService {
    @Autowired
    private ApplycationMapper am ;
    @Autowired
    private StuProjMapper sm;
    @Autowired
    private TuProjMapper tm;
    @Override
    public List<AifVo> applyPage(Page page, String field, String fval) {
        return am.applyPage(page,field,fval);
    }
    @Override
    public void dela(Integer id) {       //删除申请项目
        QueryWrapper con = new QueryWrapper();
        con.eq("item_id",id);
        String attach = am.selectOne(con).getAttachment();
        String doc = am.selectOne(con).getDocument();
        File f1 = new File(attach);
        File f2 = new File(doc);
        if(f1.exists()){
            f1.delete();
        }
        if(f2.exists()){
            f2.exists();
        }
        sm.delete(con);
        tm.delete(con);
        am.delete(con);
    }

    @Override
    public AuditVo getAuditVo(Integer tid) {
        return am.getAuditVo(tid);
    }
}
