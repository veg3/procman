package edu.mmc.controller;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sun.deploy.net.HttpResponse;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import edu.mmc.entity.Applycation;
import edu.mmc.entity.Inspection;
import edu.mmc.entity.Item;
import edu.mmc.entity.vo.*;
import edu.mmc.service.*;
import edu.mmc.util.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Huang
 * @since 2019-04-11
 */
@RestController
@RequestMapping("/apply")
public class ApplycationController {
    @Autowired
    private IInspectionService ips;
    @Autowired
    private IDloService dls;
    @Autowired
    private IItemService its;
    @Autowired
    private IApplycationService aps;
    @Autowired
    private PdfTable pt;
    @Autowired
    private JSONObject jso;

    @RequestMapping("/list")    //获取项目列表
    public TableDataVo list(Page page,HttpSession session,QueryVo qvo){
        UserUtils.handleDate(qvo);
        SystemUserVo user = (SystemUserVo) session.getAttribute("user");
        Integer table = jso.getJSONObject("table").getInteger("apply");
        List<AifVo> list = dls.aifList(page,table,user,qvo);
        return TableDataVo.okData(page.getTotal(),list);
    }
    @RequestMapping("/stustat/{tid}")          //学生提交
    public TableDataVo stustat(@PathVariable ("tid") Integer tid,HttpSession session){
        Integer rid = UserUtils.getRid(session);
        Integer table = jso.getJSONObject("table").getInteger("apply");
        String doc = dls.existdoc(tid,table);
        if(doc == null || doc.trim().equals("")){
            return TableDataVo.failMsg("申请书未上传,提交失败");
        }
        else {
            its.updStat(tid,StatusUtil.getSub().get(rid));
            return TableDataVo.okMsg("提交成功");
        }
    }

    @RequestMapping("/tubstat/{tid}")      //教师审核通过
    public TableDataVo substat(@RequestBody AuditVo avo,@PathVariable ("tid") Integer tid,HttpSession session){
        JSONObject table = jso.getJSONObject("table");
        try{
            Integer rid = UserUtils.getRid(session);
            Item item = new Item();
            item.setId(tid);
            if(rid == 2){
                item.setStatId(StatusUtil.getSub().get(rid));
            }
            else if(rid == 3){
                item.setStatId(jso.getJSONObject("stat").getInteger("initStat"));
                item.setLevel(avo.getFaLev());
                item.setStatus(table.getInteger("insp"));                  //申请通过状态
                createInspection(tid);             //创建对应的中期报告书
            }
            its.updateById(item);
            dls.backunp(table.getInteger("apply"),avo,tid);
            return TableDataVo.okMsg("提交成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return TableDataVo.failMsg("提交失败");
    }

    @RequestMapping("/backstat/{tid}")      //返回修改
    public TableDataVo backstat(@RequestBody  AuditVo avo,@PathVariable ("tid") Integer tid,HttpSession session){
        Integer table = jso.getJSONObject("table").getInteger("apply");
        return backunp(table,avo,tid,StatusUtil.getBack().get(UserUtils.getRid(session)));
    }

    @RequestMapping("/unp/{tid}")      //不通过
    public TableDataVo unp(@RequestBody  AuditVo avo,@PathVariable ("tid") Integer tid,HttpSession session){
        Integer table = jso.getJSONObject("table").getInteger("apply");
        return backunp(table,avo,tid,jso.getJSONObject("stat").getInteger("failStat"));
    }

    @PostMapping("/updoc/{tid}")    //上传文件
    public TableDataVo updoc(@RequestParam("file") CommonsMultipartFile file,HttpSession session,
                             @PathVariable("tid") Integer tid){
        if(!file.isEmpty()) {
            try {
                QueryWrapper con = getidcon(tid);
                Applycation ac = aps.getOne(con);
                String doc = ac.getDocument();
                FileUtils.delfile(doc);
                String loginid = UserUtils.getUid(session).toString();
                String path = jso.getJSONObject("doc").getString("applyDoc");
                String ofn = file.getOriginalFilename();
                String newpath = FileUtils.touchfile(loginid,path,ofn,file);
                ac.setDocument(newpath);
                aps.update(ac,con);
                return TableDataVo.okMsg("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return TableDataVo.failMsg("");
    }
    @PostMapping("/upzip/{tid}")   //上传文件
    public TableDataVo upzip(@RequestParam("file") CommonsMultipartFile file, HttpSession session,
                             @PathVariable("tid") Integer tid){
        if(!file.isEmpty()) {
            try {
                QueryWrapper con = getidcon(tid);
                String atta = aps.getOne(con).getAttachment();
                FileUtils.delfile(atta);
                String ofn = file.getOriginalFilename();
                String loginid = UserUtils.getUid(session).toString();
                String path = jso.getJSONObject("atta").getString("applyAtta");;
                String newpath = FileUtils.touchfile(loginid,path,ofn,file);
                Applycation ac = new Applycation();
                ac.setAttachment(newpath);
                aps.update(ac,con);
                return  TableDataVo.okData("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return TableDataVo.failMsg("");
    }
    @RequestMapping("/existatch/{tid}")    //获取是否带有附件
    public TableDataVo existatch(@PathVariable("tid")Integer tid, HttpSession session){
        String attach = dls.existatta(tid, jso.getJSONObject("table").getInteger("apply"));
        if(attach == null || attach.trim().equals("")){
            return  TableDataVo.failMsg("没有附件");
        }
        else{
            return TableDataVo.okMsg("yes");
        }
    }
    @RequestMapping("/existdoc/{tid}")    //获取是否带有正文
    public TableDataVo existdoc(@PathVariable("tid")Integer tid, HttpSession session){
        String doc = dls.existdoc(tid, jso.getJSONObject("table").getInteger("apply"));
        if(doc == null || doc.trim().equals("")){
            return  TableDataVo.failMsg("没有上传文件");
        }
        else{
            return TableDataVo.okMsg("yes");
        }
    }

    @RequestMapping("/dnattach/{tid}")    //下载附件
    public ResponseEntity<byte[]> dnattach(@PathVariable("tid")Integer tid){
        String filename = its.getById(tid).getName();
        return dls.attach(jso.getJSONObject("table").getInteger("apply"),tid,filename);
    }
    @RequestMapping("/dndoc/{tid}")       //下载申请书
    public ResponseEntity<byte[]> dndoc(@PathVariable("tid")Integer tid)throws Exception{
        Integer table = jso.getJSONObject("table").getInteger("apply");
        String filename = its.getById(tid).getName();
        Map<String,String> map = dls.docu(table, tid, filename);
        String temp = jso.getJSONObject("template").getString("applyPdf");
        ItemVo ivo = its.getitemVo(tid);
        Map mapc = UserUtils.treatComm(aps.getAuditVo(tid));
        ByteArrayOutputStream mbo = MergePdf.merge(pt.fillTpl(temp, ivo), pt.tmember(ivo), FileUtils.fileTobos(map.get("path")), pt.cmTable(mapc));
        return DownUtils.downbyte(map.get("filename"),mbo);
    }


    public QueryWrapper getidcon(Integer tid){
        QueryWrapper con = new QueryWrapper();
        con.eq("item_id",tid);
        return con;
    }
    public void createInspection(Integer tid){   //新建对应的中期报告书
        Inspection isp = new Inspection();
        isp.setItemId(tid);
        ips.save(isp);
    }

    public TableDataVo backunp(Integer status, AuditVo avo, Integer tid, Integer stat) {
        try {
            its.updStat(tid, stat);
            dls.backunp(status, avo, tid);
            return TableDataVo.okMsg("提交成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return TableDataVo.failMsg("提交失败");
    }

}
