package edu.mmc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.mmc.entity.vo.TableDataVo;
import edu.mmc.service.IMajorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
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
@RequestMapping("/major")
public class MajorController {
    @Autowired
    private IMajorService mjs;

    @RequestMapping("/mjNames/{faN}")
    public TableDataVo MjNames(@PathVariable("faN") String faN) {
        System.out.println("faN: " + faN);
        return TableDataVo.okData(mjs.getmjNames(faN));
    }
}
