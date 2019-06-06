package edu.mmc.util;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class DownUtils {
    public static ResponseEntity<byte[]> download(String fileName, File file) throws IOException {
        HttpHeaders headers = getHeaders(fileName);
        return new ResponseEntity(FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
    }
    
    public static ResponseEntity<byte[]> downbyte(String fileName, ByteArrayOutputStream bas) throws IOException {
        HttpHeaders headers = getHeaders(fileName);
        return new ResponseEntity(bas.toByteArray(), headers, HttpStatus.OK);
    }

    public static HttpHeaders getHeaders(String fileName) throws UnsupportedEncodingException {
        String fName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setContentDispositionFormData("attachment", fName);
        return headers;
    }
}
