package edu.mmc.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.mmc.entity.vo.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface IDloService {
      ResponseEntity<byte[]> attach(Integer status,Integer tid,String filename);
      Map docu(Integer status, Integer tid, String filename);
      String existdoc(Integer tid,Integer status);
      String existatta(Integer tid,Integer status);
      List<AifVo> aifList(Page page, Integer status, SystemUserVo vo, QueryVo qvo);
      boolean backunp(Integer status, AuditVo avo,Integer tid);
}
