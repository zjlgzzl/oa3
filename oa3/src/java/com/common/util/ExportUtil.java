package com.common.util;

import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportUtil {

	private static SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	
	public static void exportExcel(List list,String[] fields,String[] headers,OutputStream out,String sheetName) throws Exception{
		if(list == null || list.size() == 0) {
			return;
		}
		HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(sheetName);
        
        
        HSSFCellStyle headStyle = workbook.createCellStyle();
        headStyle.setVerticalAlignment((short)1);
        headStyle.setAlignment((short)2);
        HSSFFont headFont = workbook.createFont();
        headFont.setFontHeightInPoints((short)12);
        headFont.setBoldweight((short)700);
        headStyle.setFont(headFont);
        
        
        int num = 0;
        HSSFRow row = sheet.createRow(num);
        row.setHeight((short)760);
        double n = 256.0;
        for (int i = 0; i < headers.length; i++) {
        	HSSFCell cell = row.createCell(i);
        	cell.setCellValue(headers[i]);
        	cell.setCellStyle(headStyle);
        	sheet.setColumnWidth(i, (short)n * 32);
		}
        
        HSSFCellStyle colStyle = workbook.createCellStyle();
        colStyle.setVerticalAlignment((short)1);
        colStyle.setAlignment((short)2);
        colStyle.setWrapText(true);
        HSSFFont colFont = workbook.createFont();
        colFont.setFontHeightInPoints((short)12);
        colStyle.setFont(colFont);
        
        for (int i = 0; i < list.size(); ++i) {
        	row = sheet.createRow(++num);
        	Object obj = list.get(i);
        	Class<? extends Object> class1 = obj.getClass();
        	for (int j = 0; j < fields.length; j++) {
        		HSSFCell cell = row.createCell(j);
        		Method method = class1.getDeclaredMethod("get" + fields[j].substring(0,1).toUpperCase() + fields[j].substring(1));
        		Object value = method.invoke(obj);
        		String val = "";
        		if(value != null) {
        			if(value instanceof Date) {
        				val = formatter.format((Date)value);
        			}else {
        				val = value.toString();
        			}
        		}
        		cell.setCellValue(val);
        		
        		cell.setCellStyle(colStyle);
			}
        }
        
        workbook.write(out);
        out.flush();
        out.close();
        
	}
}
