package edu.mmc.factory;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

public class InitData {
    public JSONObject getData(){
        JSONObject jso = null;
        try {
            Resource resource = new ClassPathResource("/json/init.json");
            String jtext = FileUtils.readFileToString(resource.getFile(),"utf-8");
            jso = JSONObject.parseObject(jtext);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jso;
    }
}
