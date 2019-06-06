package edu.mmc.controller;

import edu.mmc.util.DownUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/dlo")
public class DLoController {
    @RequestMapping("/template")
    public ResponseEntity<byte[]> tpFile(@RequestParam("filename") String filename){
        System.out.println("hello world: "+filename);
        String tpath =  "D:/procman/template/"+filename;
        File file = new File(tpath);
        try {
             return DownUtils.download(filename, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
