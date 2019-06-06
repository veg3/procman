package edu.mmc.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.mmc.entity.vo.TableDataVo;
import edu.mmc.entity.vo.TeacherVo;
import edu.mmc.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Huang
 * @since 2019-04-11
 */
@RestController
@RequestMapping("/tch")
public class TeacherController {
    @Autowired
    private ITeacherService tcs;
    @RequestMapping("/getTup")
    public TableDataVo getTup(@RequestParam("keywords") String name){
        if(name.trim().equals("") ||name == null){
            return TableDataVo.failMsg("no params");
        }
        String con = "%"+name+"%";
        List<TeacherVo> list = tcs.getTup(con);
        if(list == null){
            return TableDataVo.failMsg("none");
        }
        else{
            System.out.println("list tec: "+list);
        }
        return TableDataVo.okData(list);
    }

    @RequestMapping("/atlist")
    public TableDataVo atlist(Page page, String faculty, String name){
         List<TeacherVo> list = tcs.atlist(page,faculty, name);
         return TableDataVo.okData(page.getTotal(),list);
    }

    @RequestMapping("/udp")
    public TableDataVo updByoid(TeacherVo tec,Long oldid){
        System.out.println("tec: "+tec +" \n oid: "+oldid);
        Boolean flag = tcs.updByoid(tec,oldid);
        if(flag){
            return TableDataVo.okMsg("更新成功");
        }else {
            return TableDataVo.failMsg("更新失败");
        }
    }
    @RequestMapping("/del/{id}")
    public TableDataVo del(@PathVariable("id") Long id){
        boolean flag = tcs.removeById(id);
        if(flag){
            return TableDataVo.okMsg("删除成功");
        }else {
            return TableDataVo.failMsg("删除失败");
        }
    }
}
