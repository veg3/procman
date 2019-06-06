package edu.mmc.controller;


import edu.mmc.entity.Faculty;
import edu.mmc.entity.vo.TableDataVo;
import edu.mmc.service.IFacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Huang
 * @since 2019-04-24
 */
@RestController
@RequestMapping("/facul")
public class FacultyController {
    @Autowired
    private IFacultyService fts;
    @RequestMapping("/facnames")
    public TableDataVo facnames(){
        return TableDataVo.okData(fts.getFaNames());
    }
    @RequestMapping("/fac2")
    @ResponseBody
    public List<String> fac2(){
        List<String> faNames = fts.getFaNames();
        return faNames;
    }
}
