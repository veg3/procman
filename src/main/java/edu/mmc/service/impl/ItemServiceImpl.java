package edu.mmc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.mmc.entity.Item;
import edu.mmc.entity.vo.*;
import edu.mmc.mapper.ItemMapper;
import edu.mmc.service.IItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements IItemService {
    @Autowired
    ItemMapper im;

    @Override
    public Boolean updvo(ItemVo vo) {
        return im.updvo(vo);
    }

    @Override
    public Boolean insVo(ItemVo vo) {
        return im.insVo(vo);
    }

    @Override
    public List<AlistVo> itemsPage(Page page, Integer rid, String fval, QueryVo qvo){
        return im.itemsPage(page,rid,fval,qvo);
    }
    @Override
    public void updStat(Integer id,Integer val){
         im.updStat(id,val);
    }

    @Override
    public List<TotalVo> gettda(List<String> str, Integer typ, QueryVo qvo) {
        return im.gettda(str,typ,qvo);
    }

    @Override
    public List<TotalVo> getsda(List<String> str, Integer sid, QueryVo qvo) {
        return im.getsda(str,sid,qvo);
    }
    @Override
    public FnsVo aifNs(Integer id) {
        return im.aifNs(id);
    }

    @Override
    public ItemVo getitemVo(Integer id) {
        return im.getitemVo(id);
    }


}
