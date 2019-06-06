package edu.mmc.service.impl;

import edu.mmc.entity.StuProj;
import edu.mmc.entity.vo.StuProjVo;
import edu.mmc.mapper.StuProjMapper;
import edu.mmc.service.IStuProjService;
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
 * @since 2019-04-17
 */
@Service
public class StuProjServiceImpl extends ServiceImpl<StuProjMapper, StuProj> implements IStuProjService {
    @Autowired
    private StuProjMapper spm;
    @Override
    public List<StuProjVo> leader(Integer tid) {
        return spm.leader(tid);
    }

    @Override
    public List<StuProjVo> otherspj(Integer tid) {
        return spm.otherspj(tid);
    }

    @Override
    public Boolean del(Integer tid, Long uid) {
        return spm.del(tid,uid);
    }

    @Override
    public Boolean save(Integer tid, Long uid) {
        return spm.save(tid,uid);
    }
}
