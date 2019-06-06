package edu.mmc.service;

import edu.mmc.entity.StuProj;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.mmc.entity.vo.StuProjVo;
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
public interface IStuProjService extends IService<StuProj> {
    List<StuProjVo> leader( Integer tid);

    List<StuProjVo> otherspj(Integer tid);

    Boolean del (Integer tid,Long uid);

    Boolean save(Integer tid,Long uid);
}
