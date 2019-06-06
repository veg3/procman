package edu.mmc.util;

import edu.mmc.entity.vo.FnsVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;

public class FileUtils {
    public static void delfile(String path){
        if(path != null){
            File ft = new File(path);
            if(ft.exists()){
                ft.delete();
            }
        }
    }
    public static void delfile(FnsVo fnsVo){
        try{
            if(fnsVo != null){
                Field[] fields = fnsVo.getClass().getDeclaredFields();
                for(Field f : fields){
                    f.setAccessible(true);
                    Object val = f.get(fnsVo);
                    if(val != null ||!val.toString().trim().equals("")){
                        File ft = new File(val.toString());
                        ft.delete();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String touchfile(String logid,String path,String ofn,MultipartFile file) throws  Exception{
        String str = postfix(ofn);
        String md = MdHex.md5(logid);
        String newpath=path+md+str;
        file.getBytes();
        File newFile=new File(newpath);
        //通过CommonsMultipartFile的方法直接写文件（注意这个时候）
        if(file != null) {
            file.transferTo(newFile);
        }
        return newpath;
    }
    public static  String postfix(String ofn){
        int pos = ofn.lastIndexOf(".");
        return  ofn.substring(pos,ofn.length());
    }

    public static ByteArrayOutputStream fileTobos(String path) throws IOException{
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        FileInputStream in = new FileInputStream(path);
        byte[] b = new byte[1024];
        int i = 0;
        while ((i = in.read(b)) != -1) {
            out.write(b, 0, b.length);
        }
        in.close();
        return  out;

    }

}
