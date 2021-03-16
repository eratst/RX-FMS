package com.pcitc.fms.common;

import com.pcitc.fms.bll.itf.impl.ExcelServiceImpl;
import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ExcelUtils {
    private static Logger log = LoggerFactory.getLogger(ExcelUtils.class);
    
    private static String fileName;
    
    public static  <T> List<T> getExcelAsList(OPCPackage file, T t) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
        
        Workbook wb = new XSSFWorkbook(file);
        
        Class clazz = t.getClass();
        //��ȡ��ǰ���������
        
        Field[] fields = clazz.getDeclaredFields();
        List<T> tList = new ArrayList<T>();
        
        //����excel
        //��õ�ǰexcel��
        Sheet sheet = wb.getSheetAt(0);
        //�����������  
        int rowNum = sheet.getLastRowNum() + 1;
        //��ȡ��������
        int colNum = sheet.getRow(0).getLastCellNum() - 1;
        
        //���set������
        String[] methodNames = new String[colNum];
        //��������ֶ���������
        String[] types = new String[colNum];
        
        //����excel�ı�ͷ
        
        for(int i = 0; i < colNum; i++){
            Row row = sheet.getRow(1);
            String name = row.getCell(i).toString();
            //ƴ��setXxx()
            methodNames[i] = "set"+Character.toUpperCase(name.charAt(0))+name.substring(1, name.length());
            
            //����object�������ֶΣ������ֶ�����
            for(int j = 0; j < fields.length; j++){
                if(name.equals(fields[j].getName())){
                    types[i] = fields[j].getType().getName();
                }
            }
        }
        
        //��ȡ��һ���Ժ������
        for(int j = 2;j < rowNum; j++){
            Row row = sheet.getRow(j);
            if(row == null){
                throw new RuntimeException("û�����ݣ�");
            }
            T obj = null;
            try {
                obj = (T)clazz.newInstance();
            } catch (InstantiationException e1) {
                log.error(e1.getMessage());
            }
            
            for(int i = 0; i < colNum; i++){
               
               
                String data = row.getCell(i) == null? null: row.getCell(i).toString();
                if(data == null||"".equals(data)){
                    continue;
                }
                Method method = null;
                if(types[i].equals("java.lang.String")){
                    method = clazz.getDeclaredMethod(methodNames[i], String.class);
                    method.invoke(obj, data);
                }
                if(types[i].equals("int")){
                    method = clazz.getDeclaredMethod(methodNames[i], int.class);
                    double d_data = Double.parseDouble(data);
                    int i_data = (int)d_data;
                    method.invoke(obj, i_data);
                }
                if(types[i].equals("java.lang.Integer")){
                    method = clazz.getDeclaredMethod(methodNames[i], Integer.class);
                   
                    double d_data = Double.parseDouble(data);
                    int i_data = (int)d_data; 
                    method.invoke(obj, i_data);
                   
                }
                if(types[i].equals("java.lang.Double")){
                    method = clazz.getDeclaredMethod(methodNames[i], Double.class); 
                    double d_data = Double.parseDouble(data);
                    method.invoke(obj, d_data);
                }
                if(types[i].equals("java.util.Date")){
                    method = clazz.getDeclaredMethod(methodNames[i], Date.class);
                    SimpleDateFormat dff = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                    Date dd = null;
                    try {
                         dd = dff.parse(data);
                    } catch (ParseException e) {
                        log.error(e.getMessage());
                    }
                    method.invoke(obj, dd);
                }
    
            } 
            
            tList.add(obj);
        }
        
        return tList;
    }
    
    
    private static Workbook getWorkbook(OPCPackage file) throws IOException{
        
        checkExcel(file);
        
        Workbook wb = null;
        InputStream is = null;
        
        try {
           
            if(fileName.endsWith("xls")){
                wb = new HSSFWorkbook(is);
            }else if(fileName.endsWith("xlsx")){
                wb = new XSSFWorkbook(is);
            }
            
        } catch (FileNotFoundException e) {

            log.error(e.getMessage());
        }
        
        return wb;
    }
    
   
    /**
     * @param file
     * @throws IOException
     */
    private static void checkExcel(OPCPackage file) throws IOException{
        if(file == null){
            
            throw new FileNotFoundException("�ļ������ڣ�");
        }
      
    }
    
  
}
