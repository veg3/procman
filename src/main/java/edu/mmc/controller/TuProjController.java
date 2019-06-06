package edu.mmc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.mmc.entity.TuProj;
import edu.mmc.entity.vo.TableDataVo;
import edu.mmc.entity.vo.TuProjVo;
import edu.mmc.service.IItemService;
import edu.mmc.service.ITuProjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Huang
 * @since 2019-04-17
 */
@RestController
@RequestMapping("/tuj")
public class TuProjController {
    @Autowired
    private ITuProjService tus;
    @ResponseBody
    @RequestMapping("/tsize/{tid}")
    public Integer tsize(@PathVariable("tid")Integer tid){
        QueryWrapper con = new QueryWrapper();
        con.eq("item_id",tid);
        Integer size = tus.count(con);
        return size;
    }
    @RequestMapping("/ftu/{tid}")
    public TableDataVo ftu(@PathVariable("tid") Integer tid){
        List list = tus.leader(tid);
        return TableDataVo.okData(list);
    }
    @RequestMapping("/sctu/{tid}")
    public TableDataVo stu(@PathVariable("tid") Integer tid){
        List list = tus.othertuj(tid);
        return TableDataVo.okData(list);
    }

    @RequestMapping("/update")
    public TableDataVo update(@RequestBody TuProj obj){
        System.out.println("obj: "+obj);
        try{
            tus.updateById(obj);
            return TableDataVo.okMsg("更新成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return TableDataVo.okMsg("更新失败");
    }
    @RequestMapping("/del/{tid}")
    public TableDataVo del(@RequestBody TuProjVo obj,@PathVariable("tid") Integer tid){
        System.out.println("obj: "+obj);
        try{
            tus.del(obj.getId(),tid);
            return TableDataVo.okMsg("删除成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return TableDataVo.okMsg("删除失败");
    }
    @RequestMapping("/tusNames/{tid}")
    public TableDataVo tusNames(@PathVariable("tid") Integer tid){
        String names = tus.tusNames(tid);
        return TableDataVo.okData(names);
    }

}
