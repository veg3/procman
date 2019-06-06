package edu.mmc.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.mmc.entity.vo.AifVo;
import edu.mmc.entity.vo.AuditVo;
import edu.mmc.entity.vo.QueryVo;
import edu.mmc.entity.vo.SystemUserVo;
import edu.mmc.mapper.DloMapper;
import edu.mmc.service.IDloService;
import edu.mmc.util.DownUtils;
import edu.mmc.util.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class DloServiceImpl implements  IDloService{
    @Autowired
    private DloMapper dlm;
    @Override                               //下载附件
    public ResponseEntity<byte[]> attach( Integer status,Integer tid,
                                            String filename){
        String path = dlm.attach(tid, status);
        File file = new File(path);
        String postfix = FileUtils.postfix(path);
        String fname = filename+postfix;
        try {
            return DownUtils.download(fname,file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override                                   //下载正文
    public Map<String,String> docu( Integer status,Integer tid,
                                          String filename){
        String path = dlm.docu(tid,status);
        String postfix = FileUtils.postfix(path);
        String fname = filename+postfix;
        Map<String,String> map = new HashMap<>();
        map.put("filename",fname);
        map.put("path",path);
        return map;
    }

    @Override                         //查看正文是否存在
    public String existdoc(Integer tid, Integer status) {
        return dlm.docu(tid,status);
    }

    @Override                        //查看附件是否存在
    public String existatta(Integer tid, Integer status) {
        return dlm.attach(tid,status);
    }

    @Override
    public List<AifVo> aifList(Page page, Integer status, SystemUserVo vo, QueryVo qvo) {
        return dlm.aifList(page,status,vo,qvo);
    }

    @Override
    public boolean backunp(Integer status, AuditVo avo, Integer tid) {
        return dlm.backunp(status,avo,tid);
    }


    public Map getMap(Integer status,Integer tid){
        Map<String,Integer> map = new HashMap<>();
        map.put("status",status);
        map.put("tid",tid);
        return map;
    }


}
