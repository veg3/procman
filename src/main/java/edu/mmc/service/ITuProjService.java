package edu.mmc.service;

import edu.mmc.entity.TuProj;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.mmc.entity.vo.TuProjVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Huang
 * @since 2019-04-17
 */
public interface ITuProjService extends IService<TuProj> {
     String tusNames(Integer tid);

     List<TuProjVo>othertuj(Integer tid);

     Boolean del(Long fid,Integer tid);

     List<TuProjVo> leader(Integer tid);

     Boolean save(Integer tid,Long uid);
}
