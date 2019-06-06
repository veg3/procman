package edu.mmc.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.mmc.entity.FinalReport;
import edu.mmc.entity.Item;
import edu.mmc.entity.vo.*;
import edu.mmc.service.IDloService;
import edu.mmc.service.IFinalReportService;
import edu.mmc.service.IItemService;
import edu.mmc.service.ITeacherService;
import edu.mmc.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Huang
 * @since 2019-04-26
 */
@RestController
@RequestMapping("/fnrp")
public class FinalReportController {
    @Autowired
    private IItemService its;
    @Autowired
    private IFinalReportService frs;
    @Autowired
    private IDloService dls;
    @Autowired
    private PdfTable pt;
    @RequestMapping("/list")    //获取项目列表
    public TableDataVo list(Page page,HttpSession session,QueryVo qvo){
        SystemUserVo user = (SystemUserVo) session.getAttribute("user");
        UserUtils.handleDate(qvo);
        List<AifVo> list = dls.aifList(page,3,user,qvo);
        return TableDataVo.okData(page.getTotal(),list);
    }

    @RequestMapping("/stustat/{tid}")          //学生提交
    public TableDataVo stustat(@PathVariable ("tid") Integer tid,HttpSession session){
        Integer rid = UserUtils.getRid(session);
        String doc = dls.existdoc(tid, 3);
        if(doc == null || doc.trim().equals("")){
            return TableDataVo.failMsg("申请书未上传,提交失败");
        }
        else {
            its.updStat(tid,StatusUtil.getSub().get(rid));
            return TableDataVo.okMsg("提交成功");
        }
    }
    @RequestMapping("/backstat/{tid}")      //返回修改
    public TableDataVo backstat(@RequestBody AuditVo avo, @PathVariable ("tid") Integer tid, HttpSession session){
        return backunp(3,avo,tid,StatusUtil.getBack().get(UserUtils.getRid(session)));
    }
    @RequestMapping("/unp/{tid}")      //不通过
    public TableDataVo unp(@RequestBody  AuditVo avo,@PathVariable ("tid") Integer tid,HttpSession session){
        return backunp(3,avo,tid,-1);
    }

    @RequestMapping("/tubstat/{tid}")      //教师审核通过
    public TableDataVo substat(@RequestBody AuditVo avo,@PathVariable ("tid") Integer tid,HttpSession session){
        return backunp(3,avo,tid,StatusUtil.getSub().get(UserUtils.getRid(session)));
    }

    @PostMapping("/updoc/{tid}")    //上传文件
    public TableDataVo updoc(@RequestParam("file") CommonsMultipartFile file, HttpSession session,
                             @PathVariable("tid") Integer tid){
        if(!file.isEmpty()) {
            try {
                QueryWrapper con = getidcon(tid);
                String doc = frs.getOne(con).getDocument();
                FileUtils.delfile(doc);
                String loginid = UserUtils.getUid(session).toString();
                String path="D:/procman/final/doc/";
                String ofn = file.getOriginalFilename();
                String newpath = FileUtils.touchfile(loginid,path,ofn,file);
                //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
                FinalReport fr = new FinalReport();
                fr.setDocument(newpath);
                frs.update(fr,con);
                return TableDataVo.okData("");
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
                String atta = frs.getOne(con).getAttachment();
                FileUtils.delfile(atta);
                String ofn = file.getOriginalFilename();
                String loginid = UserUtils.getUid(session).toString();
                String path="D:/procman/final/attach/";
                String newpath = FileUtils.touchfile(loginid,path,ofn,file);
                FinalReport fr = new FinalReport();
                fr.setAttachment(newpath);
                frs.update(fr,con);
                return  TableDataVo.okData("");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return TableDataVo.failMsg("");
    }

    @RequestMapping("/existdoc/{tid}")    //获取是否带有正文
    public TableDataVo existdoc(@PathVariable("tid")Integer tid, HttpSession session){
        String doc = dls.existdoc(tid, 3);
        if(doc == null){
            return  TableDataVo.failMsg("没有上传文件");
        }
        else{
            return TableDataVo.okMsg("yes");
        }
    }
    @RequestMapping("/existatch/{tid}")    //获取是否带有附件
    public TableDataVo existatch(@PathVariable("tid")Integer tid, HttpSession session){
        String attach = dls.existatta(tid, 3);
        if(attach == null || attach.trim().equals("")){
            return  TableDataVo.failMsg("没有附件");
        }
        else{
            return TableDataVo.okMsg("yes");
        }
    }

    @RequestMapping("/dnattach/{tid}")    //下载附件
    public ResponseEntity<byte[]> dnattach(@PathVariable("tid")Integer tid){
        String filename = its.getById(tid).getName();
        System.out.println("mapping filename: "+filename);
        return dls.attach(3,tid,filename);
    }
    @RequestMapping("/dndoc/{tid}")       //下载申请书
    public ResponseEntity<byte[]> dndoc(@PathVariable("tid")Integer tid) throws Exception{
        String filename = its.getById(tid).getName();
        Map<String,String> map = dls.docu(2, tid, filename);
        String temp1 = "D:/procman/template/final.pdf";
        String temp2 = "D:/procman/template/mit.pdf";
        FinalVo fVo = frs.getFinalVo(tid);
         AuditVo aVo = frs.getcomm(tid);
        ByteArrayOutputStream mbo = MergePdf.merge(pt.fillTpl(temp1,fVo),FileUtils.fileTobos(map.get("path")), pt.fillTpl(temp2,aVo));
        return DownUtils.downbyte(map.get("filename"),mbo);

    }

    public QueryWrapper getidcon(Integer tid){
        QueryWrapper con = new QueryWrapper();
        con.eq("item_id",tid);
        return con;
    }
    public TableDataVo backunp(Integer status,AuditVo avo,Integer tid,Integer stat){
        try {
            its.updStat(tid,stat);
            dls.backunp(status,avo,tid);
            return TableDataVo.okMsg("提交成功");
        }catch (Exception e){
            e.printStackTrace();
        }
        return TableDataVo.failMsg("提交失败");
    }
}

