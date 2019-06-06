package edu.mmc.util;

import java.io.ByteArrayOutputStream;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;

public class MergePdf {
    public static ByteArrayOutputStream merge(ByteArrayOutputStream ...outs){
        Document document = null;
        ByteArrayOutputStream bya = null;
        try {
            document = new Document();
            bya = new ByteArrayOutputStream();
            PdfCopy copy = new PdfCopy(document, bya);
            document.open();
            for(ByteArrayOutputStream op:outs){
                PdfReader reader = new PdfReader(op.toByteArray());
                int n = reader.getNumberOfPages();
                for (int j = 1; j <= n; j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);// 从当前Pdf,获取第j页
                    copy.addPage(page);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            document.close();
        }
        return  bya;
    }
}
