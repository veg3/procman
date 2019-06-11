package edu.mmc.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import com.lowagie.text.Element;
import edu.mmc.entity.vo.ItemVo;
import edu.mmc.entity.vo.StuProjVo;
import edu.mmc.entity.vo.TuProjVo;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map.Entry;
import java.util.Map;
public class PdfTable {
    private static BaseFont baseFont;
    private static Font font;
    static {
        try {
            baseFont = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            font = new Font(baseFont, 10, Font.NORMAL);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
    public  ByteArrayOutputStream tmember(ItemVo item) throws Exception {
        Document doc = new Document();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfWriter.getInstance(doc,bos);
        doc.open();
        String str2[] = new String[]{"申请团队","","姓名","年级","院系","专业","联系电话","邮箱"};
        PdfPTable table = new PdfPTable(2);
        PdfPTable table2 = new PdfPTable(8);
        PdfPTable table3 = new PdfPTable(5);
        float widths[] = {2,8};
        float widths2[] = {1.2F,3,3,3,4,4,4,4};
        float widths3[] = {1.45F,6,9,6,9};
        inittable(widths,0F,table);
        inittable(widths2,1.2F,table2);
        inittable(widths3,1.4F,table3);
        {
            String str[] = new String[] {"项目名称",item.getName(),"项目实施时间",item.getExecuteDate()};
            for(int i=0;i<str.length;i++) {
                addFixCell(str[i], table, 16F);
            }
        }
        {
            addRCell("申请团队", table2, 4);
            List<StuProjVo> stmb = item.getStmb();
            for(int i=1;i<8;i++) {
                addFixCell(str2[i], table2, 22F);
            }
            for(int j=0;j<stmb.size();j++) {
                if(j == 0) {
                    addFixCell("项目负责人", table2, 30F);
                }
                if(j == 1) {
                    addRCell("项目成员", table2, 2);
                }
                addFixCell(stmb.get(j).getName(),table2,30F);
                addFixCell(stmb.get(j).getGrade().toString(),table2,30F);
                addFixCell(stmb.get(j).getFaculty(),table2,30F);
                addFixCell(stmb.get(j).getMajor(),table2,30F);
                addFixCell(stmb.get(j).getPhone(),table2,30F);
                addFixCell(stmb.get(j).getEmail(),table2,30F);
            }
        }
        {
            List<TuProjVo> tumb = item.getTumb();
            addRCell("指导教师", table3, 2*tumb.size());
            for(int t=0;t<tumb.size();t++) {
                float height = 24F;
                addFixCell("姓名", table3, height);
                addFixCell(tumb.get(t).getName(), table3, height);
                addFixCell("研究方向", table3, height);
                addFixCell(tumb.get(t).getResDirect(), table3, height);
                addFixCell("院系", table3, height);
                addFixCell(tumb.get(t).getFaculty(), table3, height);
                addFixCell("职称", table3, height);
                addFixCell(tumb.get(t).getJobTitle(), table3, height);
            }
        }
        doc.add(table);
        doc.add(table2);
        doc.add(table3);
        doc.close();
        return  bos;
    }

    public  ByteArrayOutputStream cmTable(Map<String,String> map) throws Exception {
        Document doc = new Document();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfWriter.getInstance(doc,bos);
        doc.open();
        PdfPTable table = new PdfPTable(1);
        for(Entry<String,String> entry : map.entrySet()){
            commit(entry.getKey(), entry.getValue(), table);
        }
        doc.add(table);
        doc.close();
        return bos;
    }
    public  ByteArrayOutputStream fillTpl(String path,Object obj) throws DocumentException,IOException
                                                            ,IllegalAccessException{
        PdfReader reader = new PdfReader(path);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PdfStamper stamper = new PdfStamper(reader,bos);
        AcroFields form = stamper.getAcroFields();
        BaseFont bf = BaseFont.createFont("D:/google/simsun.ttc,1", BaseFont.IDENTITY_H,
                BaseFont.EMBEDDED);
        form.addSubstitutionFont(bf);
        if(obj != null){
            Field[] fds = obj.getClass().getDeclaredFields();
            for(Field f:fds){
                f.setAccessible(true);
                if(f.get(obj) !=null){
                    form.setField(f.getName(),f.get(obj).toString());
                }
            }
        }
        stamper.setFormFlattening(true);
        stamper.close();
        reader.close();
        return  bos;
    }

    public  void addFixCell(String str,PdfPTable table,Float height) {
        PdfPCell cell = patoCell(str,font);
        cell.setMinimumHeight(height);
        addMdCell(cell, table);
    }
    public  void addMdCell(PdfPCell cell,PdfPTable table) {
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);
    }
    public  void addRCell(String str,PdfPTable table,Integer row) {
        PdfPCell cell = patoCell(str,font);
        cell.setRowspan(row);
        addMdCell(cell, table);
    }

    public  void commit(String header,String content,PdfPTable table) {
        Font font = new  Font(baseFont, 13, Font.BOLD);
        Paragraph p = new Paragraph(header+"\n\n",font);
        if(content !=null) {
            Font font2 = new  Font(baseFont, 11, Font.NORMAL);
            Paragraph p2 = new Paragraph(content+"\n\n",font2);
            p2.setSpacingBefore(20);
            p.add(p2);
        }
        PdfPCell cell = new PdfPCell(p);
        cell.setHorizontalAlignment(Element.ALIGN_JUSTIFIED);
        cell.setMinimumHeight(90F);
        table.addCell(cell);
    }
    public  PdfPCell patoCell(String str,Font font){
        Paragraph p= new Paragraph(str,font);
        return new PdfPCell(p);
    }

    public  void inittable(float widths[],float space,PdfPTable table) {
        try {
            table.setWidths(widths);
            table.setSpacingBefore(space);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}
