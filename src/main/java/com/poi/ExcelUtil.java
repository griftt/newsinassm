package com.poi;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.util.SystemOutLogger;

public class ExcelUtil<T> {
	
	public void exportExcel(String title,String pattern,List<T>data,OutputStream out) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		
		//新建一个工作簿
		HSSFWorkbook workBook=new HSSFWorkbook();
		//新建一个表格
		HSSFSheet sheet =workBook.createSheet(title);
		
		//设置生成表格的默认列宽度为15字节
		sheet.setDefaultColumnWidth(15);
		//生成一个样式
		HSSFCellStyle style = workBook.createCellStyle();
		//设置样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = workBook.createFont();
		font.setColor(HSSFColor.BLACK.index);
		font.setFontHeightInPoints((short)12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		//把字体应用到样式中
		style.setFont(font);
		// 生成并设置另一个样式
				HSSFCellStyle style2 = workBook.createCellStyle();
				style2.setFillForegroundColor(HSSFColor.BRIGHT_GREEN.index);
				style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
				style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
				style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
				style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
				style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
				style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
				// 生成另一个字体
				HSSFFont font2 = workBook.createFont();
				font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
				// 把字体应用到当前的样式
				style2.setFont(font2);
				//中间还有很多可以设置的
				//设置表格头
				String [] headers2=null;
				if(!data.isEmpty()){
					T t1 = data.get(0);
					Class<? extends Object> c1 = t1.getClass();
					Field[] fields = c1.getDeclaredFields();
					headers2=new String[fields.length];
					for(int i=0;i<fields.length;i++){
						headers2[i]=fields[i].getName();
					}
				}
				HSSFRow row = sheet.createRow(0);
				for(int i=0;i<headers2.length;i++){
					HSSFCell cell = row.createCell(i);
					cell.setCellStyle(style);
					HSSFRichTextString text = new HSSFRichTextString(headers2[i]);
					cell.setCellValue(text);
				}
				//遍历集合数据，产生数据行
				Iterator<?> it = data.iterator();
				int index=0;
				System.err.println("ok");
			while(it.hasNext()){
					Object t = it.next();
					index++;
					Class<?> tcl = t.getClass();
					HSSFRow dataRow = sheet.createRow(index);
					Field[] fields = tcl.getDeclaredFields();
					for(int j=0;j<fields.length;j++){
				    
						String methodName="get"+fields[j].getName().substring(0, 1).toUpperCase()
								+fields[j].getName().substring(1);
						Method m=tcl.getMethod(methodName,new Class[]{});
						Object value = m.invoke(t,new Object[]{});
						HSSFCell dataCell = dataRow.createCell(j);
						dataCell.setCellStyle(style2);
						if(value instanceof Date){
							Date date=(Date) value;
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String format = sdf.format(date);
							dataCell.setCellValue(format);
							continue;
						}
						 HSSFRichTextString textString = new HSSFRichTextString(String.valueOf(value));
						 dataCell.setCellValue(textString);
						
					}
				}
				
		
		try {
			workBook.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
