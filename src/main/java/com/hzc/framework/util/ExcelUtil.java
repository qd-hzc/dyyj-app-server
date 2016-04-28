package com.hzc.framework.util;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yinbin on 2015/5/8.
 */
public class ExcelUtil {
    /**
     * 根据模板生成Excel文件.
     * @param templateFileName 模板文件.
     * @param list 模板中存放的数据.
     * @param resultFileName 生成的文件.
     */
    public void createExcel(String templateFileName, List<?> list, String resultFileName){
        try {
            //创建XLSTransformer对象
            XLSTransformer transformer = new XLSTransformer();

            //获取java项目编译后根路径
            URL url = this.getClass().getClassLoader().getResource("");

            //得到模板文件路径
            String srcFilePath = url.getPath() + templateFileName;
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("list", list);
            String destFilePath = url.getPath() + resultFileName;

            //生成Excel文件
            transformer.transformXLS(srcFilePath, map, destFilePath);
        } catch (Exception e) {
            throw new RuntimeException("error happens...", e);
        }
    }

    /**
     * 根据模板生成Excel文件.
     * @param templateFileName 模板文件.
     * @param list 模板中存放的数据.
     * @param outputStream 生成的文件.
     */
    public void writeExcel(String templateFileName, List<?> list, OutputStream outputStream){
        try {
            //创建XLSTransformer对象
            XLSTransformer transformer = new XLSTransformer();

            //获取java项目编译后根路径
            URL url = this.getClass().getClassLoader().getResource("");

            //得到模板文件路径
            String srcFilePath = url.getPath() + templateFileName;
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("list", list);
//            String destFilePath = url.getPath() + resultFileName;

            //生成Excel文件
//            transformer.transformXLS(srcFilePath, map, destFilePath);
            BufferedInputStream is = new BufferedInputStream(new FileInputStream(srcFilePath));
            HSSFWorkbook workbook = transformer.transformXLS(is, map);
//            BufferedOutputStream os = new BufferedOutputStream(outputStream);
            workbook.write(outputStream);
            is.close();
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException("error happens...", e);
        }
    }

}
