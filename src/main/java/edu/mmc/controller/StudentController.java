package edu.mmc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.org.glassfish.gmbal.ParameterNames;
import edu.mmc.entity.Student;
import edu.mmc.entity.vo.TableDataVo;
import edu.mmc.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Huang
 * @since 2019-04-11
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private IStudentService sts;
    @RequestMapping("/alist")
    public TableDataVo alist(Page page,String faculty, String major, String name){
        List list = sts.aslist(page, faculty, major, name);
        return TableDataVo.okData(page.getTotal(),list);
    }
    @RequestMapping("/del/{id}")
    public TableDataVo del(@PathVariable("id") Long id){
         boolean flag = sts.removeById(id);
         if(flag){
             return TableDataVo.okMsg("删除成功");
         }else {
             return TableDataVo.failMsg("删除失败");
         }
    }
    @RequestMapping("/udp")
    public TableDataVo udp(Student stu,Long oldid){
        QueryWrapper con = new QueryWrapper();
        con.eq("id",oldid);
        boolean flag = sts.updByoid(stu,oldid);
        if(flag){
            return TableDataVo.okMsg("更新成功");
        }else {
            return TableDataVo.failMsg("更新失败");
        }
    }
    @RequestMapping("/secs")
    public TableDataVo secs(@RequestParam("keywords") String id){
        if(id.length() >= 11){
            QueryWrapper con = new QueryWrapper();
            con.eq("id", id);
            List list = sts.list(con);
            if(Objects.nonNull(list) && list.size()!=0 ){
                System.out.println("size: "+list.size());
                return TableDataVo.okData(list);
            }
            else {
                return TableDataVo.failMsg("没有该学生信息");
            }
        }
        return null;
    }
}
