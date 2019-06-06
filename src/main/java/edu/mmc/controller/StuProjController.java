package edu.mmc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.mmc.entity.StuProj;
import edu.mmc.entity.vo.StuProjVo;
import edu.mmc.entity.vo.TableDataVo;
import edu.mmc.service.IItemService;
import edu.mmc.service.IStuProjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Huang
 * @since 2019-04-17
 */
@RestController
@RequestMapping("/stup")
public class StuProjController {
    @Autowired
    private IStuProjService sps;
    @RequestMapping("/leader/{tid}")
    public TableDataVo leader(@PathVariable("tid") Integer tid){
        List<StuProjVo> list = sps.leader(tid);
        return  TableDataVo.okData(list);
    }
    @RequestMapping("/ostu/{tid}")
    public TableDataVo ostu(@PathVariable("tid") Integer tid){
        List<StuProjVo> ls = sps.otherspj(tid);
        return TableDataVo.okData(ls);
    }
    @RequestMapping("/update")
    public TableDataVo update(@RequestBody StuProj obj){
        System.out.println("obj: "+obj);
        try{
            sps.updateById(obj);
            return TableDataVo.okMsg("更新成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return TableDataVo.okMsg("更新失败");
    }
    @RequestMapping("/del/{tid}")
    public TableDataVo del(StuProjVo obj,Integer tid){
        System.out.println("obj: "+obj);
        try{
            sps.del(tid,obj.getId());
            return TableDataVo.okMsg("删除成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return TableDataVo.okMsg("删除失败");
    }
    @RequestMapping("/numb/{tid}")
    public TableDataVo numb(@PathVariable("tid") Integer tid){
        return TableDataVo.okData(getCapacity(tid));
    }

    public Integer getCapacity(Integer tid){
        QueryWrapper con = new QueryWrapper();
        con.eq("item_id",tid);
        return sps.count(con);
    }
}
