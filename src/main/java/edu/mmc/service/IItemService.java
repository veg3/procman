package edu.mmc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.mmc.entity.Item;
import com.baomidou.mybatisplus.extension.service.IService;
import edu.mmc.entity.vo.*;
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
public interface IItemService extends IService<Item> {
     Boolean updvo(ItemVo vo);
     Boolean insVo(ItemVo vo);
     List<AlistVo>  itemsPage(Page page, Integer rid, String fval, QueryVo qvo);
     void updStat(Integer id,Integer val);
     List<TotalVo> gettda(List<String> str, Integer typ, QueryVo qvo);
     List<TotalVo> getsda(List<String> str, Integer sid, QueryVo qvo);
     FnsVo aifNs(Integer id);
     ItemVo getitemVo(@Param("id") Integer id);
}
