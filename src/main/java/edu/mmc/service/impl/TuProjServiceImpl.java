package edu.mmc.service.impl;

import edu.mmc.entity.TuProj;
import edu.mmc.entity.vo.TuProjVo;
import edu.mmc.mapper.TuProjMapper;
import edu.mmc.service.ITuProjService;
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
public class TuProjServiceImpl extends ServiceImpl<TuProjMapper, TuProj> implements ITuProjService {
    @Autowired
    TuProjMapper tm;
    @Override
    public String tusNames(Integer tid) {
        return tm.tusNames(tid);
    }

    @Override
    public List<TuProjVo> othertuj(Integer tid) {
        return tm.othertuj(tid);
    }

    @Override
    public Boolean del(Long fid, Integer tid) {
        return tm.del(fid,tid);
    }

    @Override
    public List<TuProjVo> leader(Integer tid) {
        return tm.leader(tid);
    }

    @Override
    public Boolean save(Integer tid, Long uid) {
        return tm.save(tid,uid);
    }

}
