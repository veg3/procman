package edu.mmc.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.mmc.entity.*;
import edu.mmc.entity.vo.*;
import edu.mmc.service.*;
import edu.mmc.util.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Huang
 * @since 2019-04-17
 */
@RestController
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private IApplycationService aps;
    @Autowired
    private IItemService its;
    @Autowired
    private IStuProjService stps;
    @Autowired
    private ITuProjService tus;

    @RequestMapping("/list")
    public TableDataVo list(Page page,HttpSession session,QueryVo qvo){
        UserUtils.handleDate(qvo);
        List<AlistVo> list = getList(page,session,qvo);
        return TableDataVo.okData(page.getTotal(),list);
    }
    @PostMapping("/save/{stk}")    //保存项目
    public TableDataVo save(ItemVo itemVo,HttpSession session,@PathVariable("stk") String stk){
        boolean tokenValid = TokenProcessor.getInstance().isTokenValid(session, stk);
        if(tokenValid){                  //防止表单的重复提交
            TokenProcessor.getInstance().resetToken(session);
            System.out.println("itevo: "+itemVo);
            System.out.println("svo: "+itemVo.getStmb());
            System.out.println("tvo: "+itemVo.getTumb());
            try{
                its.insVo(itemVo);
                int tid = itemVo.getId();
                savets(itemVo,tid);
                Applycation  ap = new Applycation();
                ap.setItemId(tid);
                aps.saveOrUpdate(ap);
                return TableDataVo.okMsg("保存成功");
            }catch (Exception e){
                throw new RuntimeException();

            }
        }
        else{
            TableDataVo.okMsg("保存成功");
        }
        return TableDataVo.failMsg("保存失败");
    }
    @RequestMapping("/update/{stk}/{tid}")  //更新项目
    public TableDataVo updvo(ItemVo itemVo,@PathVariable("stk") String stk, @PathVariable("tid")Integer tid,HttpSession session){
        System.out.println("update item: "+itemVo+" \ntid:"+tid);
        boolean tokenValid = TokenProcessor.getInstance().isTokenValid(session, stk);
        if(tokenValid) {
            System.out.println("hello world2");
            itemVo.setId(tid);
            its.updvo(itemVo);
            System.out.println("aaaa1");
            QueryWrapper con = new QueryWrapper();
            con.eq("item_id",tid);
            tus.remove(con);stps.remove(con);
            System.out.println("aaaa");
            savets(itemVo,tid);
            return TableDataVo.okMsg("更新成功");
        }
        return TableDataVo.failMsg("更新失败");
    }
    @RequestMapping("/itvo/{tid}")
    public TableDataVo itvo(@PathVariable("tid") Integer tid){
        ItemVo itemVo = its.getitemVo(tid);
        System.out.println("itemVo :"+itemVo);
        return TableDataVo.okData(itemVo);
    }
    @RequestMapping("/del/{id}")  //删除项目
    public TableDataVo del(@PathVariable("id") Integer id){
        try{
            FnsVo fnsVo = its.aifNs(id);
            its.removeById(id);
            FileUtils.delfile(fnsVo);
            return  TableDataVo.okMsg("删除成功");
        }catch (Exception e){
           throw new RuntimeException();
        }
    }

    @RequestMapping("/saveToken/{uid}")
    public TableDataVo saveToken(@PathVariable("uid") Long uid,HttpSession session){
        String token = TokenProcessor.getInstance().saveToken(session, uid.toString());
        return TableDataVo.okData(token);
    }
    @RequestMapping("/export")
    public ResponseEntity<byte[]> export(HttpSession session, @RequestParam("param") String [] param){
        List<AlistVo> list = getList(null,session,null);
        String filename = "export.xls";
        String path = "D:/procman/template/"+filename;
        InputStream in = null;
        ByteArrayOutputStream bas = null;
        try {
            in = new FileInputStream(path);
            POIFSFileSystem fs=new POIFSFileSystem(in);
            Workbook wb=new HSSFWorkbook(fs);
            ExportUtil.fillExcelData(wb,param,list);
            bas = new ByteArrayOutputStream();
            wb.write(bas);
            return DownUtils.downbyte(filename,bas);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                bas.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       return  null;
    }
    @RequestMapping("/chartbar")
    public TableDataVo chartbar(@RequestBody QueryVo qvo,HttpSession session) {
        List<String> fastr = Arrays.asList(qvo.getFaculty().split(","));
        UserUtils.handleDate(qvo);
        String legend[][] = new String[][]{{"创新训练", "创业训练", "创业实践"},{"不通过","待审核","已结题"}};
        String title[] = {"各院系项目类型申请情况","各院系项目审核进展情况"};
        Integer tda[] = new Integer[]{0, 1, 2};
        List<OptionEntity> oplist = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap();
        for(int m=0;m<title.length;m++) {
            List<List<TotalVo>> dli = new ArrayList<>();     //获取series数据
            List<TotalVo> list ;
            for (int i = 0; i < tda.length; i++) {
                if(m==0){
                    list = its.gettda(fastr, tda[i], qvo);
                }
                else {
                    list = its.getsda(fastr, tda[i], qvo);
                }
                dli.add(list);
                if (i == legend[m].length - 1) {
                    OptionEntity op = ChartHandle.opt(dli, title[m],legend[m]);
                    oplist.add(op);
                }
            }
        }
        resultMap.put("option",oplist);
        return TableDataVo.okData(resultMap);
    }
    @RequestMapping("/tui/{id}")
    public TableDataVo tui(@PathVariable("id") Integer id){
         Item item = its.getById(id);
         HashMap<String,Object> map = new HashMap<>();
         String names = tus.tusNames(id);
         map.put("intro",item.getIntro());
         map.put("tnames",names);
         return  TableDataVo.okData(map);
    }
    public List<AlistVo> getList(Page page,HttpSession session,QueryVo qvo){
        SystemUserVo user = (SystemUserVo) session.getAttribute("user");
        String fval = user.getUserId().toString();
        Integer rid = user.getRoleId();
        if(rid == 3){
            fval = user.getFaculty();
        }
        return its.itemsPage(page,rid,fval,qvo);
    }


    public void savets(ItemVo itemVo,Integer tid){
        for(StuProjVo vo :itemVo.getStmb()){
            stps.save(tid,vo.getId());
        }
        for(TuProjVo vo :itemVo.getTumb()){
            tus.save(tid,vo.getId());
        }
    }
}