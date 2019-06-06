package edu.mmc.util;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Field;
import java.util.List;

public class ExportUtil {
    public static void fillExcelData (Workbook wb,String []fdn, List list){
        int rowIndex=1;
        Sheet sheet=wb.getSheetAt(0);
        for(Object obj : list){
            Row row=sheet.createRow(rowIndex++);
            for(int i=0;i<fdn.length;i++){
                try {
                    Field field = obj.getClass().getDeclaredField(fdn[i]);
                    field.setAccessible(true);
                    row.createCell(i).setCellValue(field.get(obj).toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
