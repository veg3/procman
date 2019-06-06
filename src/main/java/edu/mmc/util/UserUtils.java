package edu.mmc.util;

import com.sun.org.apache.xml.internal.utils.Hashtree2Node;
import edu.mmc.entity.vo.AuditVo;
import edu.mmc.entity.vo.QueryVo;
import edu.mmc.entity.vo.SystemUserVo;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class UserUtils {

    public static Integer getRid(HttpSession session){
        return ((SystemUserVo) session.getAttribute("user")).getRoleId();
    }
    public static Long getUid(HttpSession session){
        return ((SystemUserVo) session.getAttribute("user")).getUserId();
    }

    public static Map treatComm(AuditVo vo){
        Map<String,String> map = new HashMap<>();
        map.put("导师意见",vo.getTuComm());
        map.put("院系意见",vo.getFaComm());
        return  map;
    }
    public static void handleDate(QueryVo qvo){  //处理时间范围
        if(qvo != null){
            if (qvo.getExecuteDate()==null || qvo.getExecuteDate().trim().equals("")) {
                qvo.setExecuteDate(null);
            }
            else{
                String[] spl = qvo.getExecuteDate().split("~");
                qvo.setSufDate(spl[0].trim());
                qvo.setPosDate(spl[1].trim());
            }
        }
    }
}
