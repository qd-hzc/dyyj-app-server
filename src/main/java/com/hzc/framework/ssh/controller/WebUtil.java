/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package com.hzc.framework.ssh.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hzc.framework.ssh.controller.validate.ValidationException;
import com.hzc.framework.ssh.controller.validate.anno.NotNull;
import com.hzc.framework.ssh.controller.validate.anno.Regexp;
import com.hzc.framework.ssh.controller.validate.anno.RegexpType;
import com.hzc.framework.util.ExcelUtil;
import com.hzc.framework.util.SshConstant;
import com.hzc.framework.util.DateJsonValueProcessor;
import com.hzc.top.util.factory.alias.W;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author YinBin
 *         此类是Controller（最好只在Controller中使用，但实际也可以用于其他层，比如Service层）中使用的工具方法，目的是为操作request和response提供极大地方便。
 */
public class WebUtil {

    public static final String JSP_PATH = "path";
    /**
     * 上传文件最大限制 1G
     */
    public static final int MAX_POST_SIZE = 1024 * 1024 * 1024;
    public static final JsonConfig JSON_CONFIG = new JsonConfig();
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_NO_SECOND = "yyyy-MM-dd HH:mm";
    public static final String DATE_FORMAT_NO_TIME = "yyyy-MM-dd";

    static {
        JSON_CONFIG.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
        JSON_CONFIG.registerJsonValueProcessor(Timestamp.class, new DateJsonValueProcessor("yyyy-MM-dd"));
    }

    private static Logger log = Logger.getLogger(WebUtil.class);

    public static Date parseDate(String dateStr, String format) throws RuntimeException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static Date parseDate(String dateStr) throws RuntimeException {
        return parseDate(dateStr, DATE_FORMAT);
    }

    public static String format(Date date, String format) {
        return FastDateFormat.getInstance(format).format(date);
    }

    public static String format(Date date) {
        return FastDateFormat.getInstance(DATE_FORMAT).format(date);
    }

    public static void writeText(String Text) throws RuntimeException {
        write("text/html", Text);
    }

    public static void writeExcel(String templateFile, String fileName, List list) throws IOException {
        HttpServletResponse resp = W.getResp();
        resp.setContentType("application/vnd.ms-excel");
        resp.addHeader("Content-Disposition", "attachment;   filename=\"" + fileName + ".xls" + "\"");
        new ExcelUtil().writeExcel(templateFile, list, resp.getOutputStream());
    }

    /**
     * 向response写一个流
     *
     * @param is
     * @throws IOException
     */
    public static void write(InputStream is) throws RuntimeException {
        try {
            write(IOUtils.toByteArray(is));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 向reponse写一个对象，这个对象会被自动序列化为JSON字符串格式
     *
     * @param obj 普通javaBean
     * @throws IOException
     */
    @Deprecated
    private static void writeJson(Object obj) throws RuntimeException {
        try {
            HttpServletResponse response = ActionContext.getResp();
            response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                // obj instanceof Map
                if (obj instanceof List || obj.getClass().isArray()) {
                    JSONArray jSONArray = JSONArray.fromObject(obj);
                    out.print(jSONArray);
                } else {
                    JSONObject jSONObject = JSONObject.fromObject(obj);
                    out.print(jSONObject);
                }
            } finally {
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * {
     * success:true/false,
     * message:''
     * }
     *
     * @param b
     * @param message
     * @throws IOException
     */
    public static void writeJson(Boolean b, String message) throws RuntimeException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", b);
        map.put("message", message);
        writeJsonObject(map);
    }

    public static void writeJson(String str) {
        write("text/javascript", str);
    }

//    public static void wirteError(String errorMessage) throws RuntimeException {
//        try {
//            WebUtil.writeJson(false,errorMessage);
////            HttpServletResponse resp = ActionContext.getResp();
////            resp.reset();
////            resp.resetBuffer();
////            resp.setStatus(200);
//            //resp.setStatus(500, errorMessage);
////            resp.setHeader("error_message", errorMessage);
////            resp.sendError(500, errorMessage);
////            resp.flushBuffer();
////            PrintWriter writer = resp.getWriter();
////            writer.println(errorMessage);
////            writer.flush();
////            writer.close();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }

    /**
     * 将一个javaBean转换为等价的JSON字符串格式
     *
     * @param obj
     * @return
     */
    public static String javaObjectConvertJson(Object obj) {
//        JSON.toJSONString(arg0)
        return JSONObject.fromObject(obj, JSON_CONFIG).toString();
    }

    /**
     * 将javaArray转换为等价的JSON格式字符串
     *
     * @param obj
     * @return
     */
    public static String javaArrayConvertJson(Object obj) {
        return JSONArray.fromObject(obj, JSON_CONFIG).toString();
    }

    /**
     * 将JSON格式字符串转化为等价的javaBean对象
     *
     * @param c    转化为的对象的类型
     * @param json JSON字符串
     * @param <T>
     * @return
     */
    public static <T> T jsonConvertJavaObject(Class<T> c, String json) {
        return (T) JSONObject.toBean(JSONObject.fromObject(json, JSON_CONFIG), c);
    }

    /**
     * 将JSON格式字符串转化为等价的javaArray类型
     *
     * @param c    数组中的元素的类型
     * @param json JSON格式字符串
     * @param <T>  返回的数组的泛型类型
     * @return
     */
    public static <T> Object jsonConvertJavaArray(Class<T> c, String json) {
        return JSONArray.toArray(JSONArray.fromObject(json, JSON_CONFIG), c);
    }

    /**
     * 向reponse写一个对象，这个对象会被自动序列化为JSON字符串格式
     *
     * @param obj 普通javaBean
     * @throws IOException
     */
    public static void writeJsonObject(Object obj) throws RuntimeException {
        wirteJson(JSONObject.fromObject(obj, JSON_CONFIG));
    }

    public static String getJsonObjectString(Object obj) throws RuntimeException {
        return JSONObject.fromObject(obj, JSON_CONFIG).toString();
    }

    public static String getJsonArrayString(Object obj) throws RuntimeException {
        return JSONArray.fromObject(obj, JSON_CONFIG).toString();
    }

    public static void wirteJsonForDataTable(List list) throws RuntimeException {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("dataList", list);
        json.put("iTotalRecords", ((Page) list).getTotal());
        json.put("sEcho", getInteger("sEcho"));
        json.put("iTotalDisplayRecords", ((Page) list).getTotal());
        writeJsonObject(json);
    }

    /**
     * 向reponse写一个数组，这个数组对象会被自动序列化为JSON字符串格式
     *
     * @param obj 普通javaBean
     * @throws IOException
     */
    public static void writeJsonArray(Object obj) throws RuntimeException {
        wirteJson(JSONArray.fromObject(obj, JSON_CONFIG));
    }

    /**
     * 向reponse写一个数组，这个数组对象会被自动序列化为JSON字符串格式
     *
     * @param obj 普通javaBean
     * @throws IOException
     */
    public static void writeJsonArray(List obj) throws RuntimeException {
        try {
            wirteJsonForDataTable(obj);
        } catch (Exception e) {//如果不是datatable的，那就是普通的ajax 返回list数据
            wirteJson(JSONArray.fromObject(obj, JSON_CONFIG));
        }
    }

    private static void wirteJson(Object obj) throws RuntimeException {
        write("application/json", obj);
    }

    /**
     * 向response写一个html字符串
     *
     * @param html
     * @throws IOException
     */
    public static void writeHtml(String html) throws RuntimeException {
        write("text/html", html);
    }

    /**
     * 向response写一段脚本
     *
     * @param script
     * @throws Exception
     */
    public static void writeScript(String script) throws RuntimeException {
        writeHtml("<script type='text/javascript'>" + script + "</script>");
    }

    /**
     * 下载文件方法
     *
     * @param attachFile 文件数组
     * @param fileName   下载提示的文件名称
     * @throws IOException
     */
    public static void write(byte[] attachFile, String fileName) throws RuntimeException {
        try {
            HttpServletResponse resp = ActionContext.getResp();
            resp.addHeader("Content-Disposition", "attachment; filename=" + fileName);
            ServletOutputStream outputStream = resp.getOutputStream();
            outputStream.write(attachFile);
            outputStream.flush();
//            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 向response输出一个二进制流
     *
     * @param attachFile
     * @throws IOException
     */
    public static void write(byte[] attachFile) throws RuntimeException {
        try {
            HttpServletResponse resp = ActionContext.getResp();
            ServletOutputStream outputStream = resp.getOutputStream();
            outputStream.write(attachFile);
            outputStream.flush();
//            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 向response输出一个二进制流
     * 注意仅仅可以输出小于10m的文件，如果输出大文件请使用：downloadCall
     *
     * @param attachFile
     * @throws IOException
     */
    public static void download(byte[] attachFile, String fileName) throws RuntimeException {
        try {
            HttpServletResponse resp = ActionContext.getResp();
            resp.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO-8859-1"));
            resp.addHeader("Content-Length", "" + attachFile.length);
//            resp.setContentType("application/octet-stream");
            ServletOutputStream outputStream = resp.getOutputStream();
            outputStream.write(attachFile);
            outputStream.flush();
//            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 向response输出一个文件
     * 注意仅仅可以输出小于10m的文件，如果输出大文件请使用：downloadCall
     *
     * @param file
     * @throws IOException
     */
    public static void download(File file, String fileName) throws RuntimeException {
        try {
            HttpServletResponse resp = ActionContext.getResp();
            resp.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "ISO-8859-1"));
//            resp.addHeader("Content-Length", "" + attachFile.length);
//            resp.setContentType("application/octet-stream");
            ServletOutputStream outputStream = resp.getOutputStream();
            outputStream.write(IOUtils.toByteArray(new FileInputStream(file)));
            outputStream.flush();
//            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 向response写入任何对象，并需指定对象类型
     *
     * @param contentType response的contentType，内容类型
     * @param object      任何对象
     * @throws IOException
     */
    public static void write(String contentType, Object object) throws RuntimeException {
        try {
            HttpServletResponse response = ActionContext.getResp();
            //System.out.println("json object:" + object);
            response.setContentType(contentType + ";charset=UTF-8");
            PrintWriter writer = response.getWriter();
//            try {
            writer.print(object);
//            } finally {
            writer.flush();
//                writer.close();
//            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 向response写一个文件流，并指定类型，浏览器可以根据类型调用不同的com组件进行不同的处理
     *
     * @param contentType
     * @param attachFile
     * @throws IOException
     */
    public static void write(String contentType, byte[] attachFile) throws RuntimeException {
        try {
            HttpServletResponse resp = ActionContext.getResp();
            resp.setContentType(contentType);
            ServletOutputStream outputStream = resp.getOutputStream();
            outputStream.write(attachFile);
            outputStream.flush();
//            outputStream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 重定向到某个页面
     *
     * @param location
     * @throws IOException
     */
    public static void redirect(String location) throws RuntimeException {
        try {
            WebUtil.getResp().sendRedirect(location);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 前进道某个服务器端的页面或者servlet
     *
     * @param path 前进到的地址
     * @throws ServletException
     * @throws IOException
     */
    public static void forward(String path) throws RuntimeException {
        ActionContext.getReq().setAttribute(WebUtil.JSP_PATH, path);
        forward();
    }

    /**
     * 默认前进方法，会自动从上下文中取出JSP_PATH属性作为跳转的地址。
     * 并会默认保留所有请求链中的属性。
     *
     * @throws ServletException
     * @throws IOException
     */
    private static void forward() throws RuntimeException {
        try {
            HttpServletRequest request = ActionContext.getReq();
            HttpServletResponse response = ActionContext.getResp();
            if (response.isCommitted()) {
                return;
            }
            String jspPath = null;
            String jspPathParam = request.getParameter(JSP_PATH);
            if (StringUtils.isEmpty(jspPathParam)) {
                Object jspPathObjAttr = request.getAttribute(JSP_PATH);
                if (null == jspPathObjAttr) {
                    Object jspPathObjSess = request.getSession().getAttribute(JSP_PATH);
                    if (null != jspPathObjSess) {
                        jspPath = (String) jspPathObjSess;
                    }
                } else {
                    jspPath = (String) jspPathObjAttr;
                }
            } else {
                jspPath = jspPathParam;
            }
            if (null == jspPath) {
                throw new ServletException("程序错误，服务器端跳转页面，没有找到”jspPath“属性！");
            }
            request.getRequestDispatcher(jspPath).forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 默认将文件临时存储到webroot目录下的upload文件夹中 服务器重启后，将会消失
     *
     * @param c
     * @param ufc
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T upload(Class<T> c, UploadFileCall ufc) throws RuntimeException {
        return upload("/upload", c, ufc);
    }

    /**
     * 文件上传+表单提交数据的封装
     *
     * @param path
     * @param c
     * @param ufc
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T upload(String path, Class<T> c, UploadFileCall ufc) throws RuntimeException {
        try {
            HttpServletRequest request = getReq();

            ServletContext servletContext = getServletContext();
            File file = new File(servletContext.getRealPath(path));
            if (!file.exists())
                file.mkdir();

            MultipartRequest multi =
                    new MultipartRequest(request, file.getAbsolutePath(), 1024 * 1024 * 1024, "UTF-8", new DefaultFileRenamePolicy());
            Enumeration params = multi.getParameterNames();
            T obj = c.newInstance();
            Field[] declaredFields = c.getDeclaredFields();

            // 循环表单值，封装到属性中,字段不多，所以效率还可以
            labelNext:
            while (params.hasMoreElements()) {
                String name = (String) params.nextElement();
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    String fieldName = field.getName();
                    if (name.equals(fieldName)) {
                        String value = multi.getParameter(name);
                        if (null == value) {
                            continue;
                        }
                        Class<?> type = field.getType();
                        if (type == Long.class) {
                            field.set(obj, Long.parseLong(value));
                        } else if (type == String.class) {
                            field.set(obj, value);
                        } else if (type == Byte.class) {
                            field.set(obj, Byte.parseByte(value));
                        } else if (type == Integer.class) {
                            field.set(obj, Integer.parseInt(value));
                        } else if (type == Character.class) {
                            field.set(obj, value.charAt(0));
                        } else if (type == Boolean.class) {
                            field.set(obj, Boolean.parseBoolean(value));
                        } else if (type == Double.class) {
                            field.set(obj, Double.parseDouble(value));
                        } else if (type == Float.class) {
                            field.set(obj, Float.parseFloat(value));
                        }
                        continue labelNext;
                    }
                }
            }
            ufc.form(obj, multi);
            Enumeration files = multi.getFileNames();
            while (files.hasMoreElements()) {
                String name = (String) files.nextElement();
                String filename = multi.getFilesystemName(name);
                String originalFilename = multi.getOriginalFileName(name);
                String type = multi.getContentType(name);
                File f = multi.getFile(name);
                ufc.file(obj, f, filename, originalFilename, type);
            }
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 文件上传
     *
     * @param path
     * @param ufc
     * @return
     * @throws Exception
     */
    public static void upload(String path, UploadFileCall ufc) throws RuntimeException {
        try {
            HttpServletRequest request = getReq();
            ServletContext servletContext = getServletContext();
            File file = new File(servletContext.getRealPath(path));
            if (!file.exists())
                file.mkdirs();
            MultipartRequest multi =
                    new MultipartRequest(request, file.getAbsolutePath(), 1024 * 1024 * 1024, "UTF-8", new DefaultFileRenamePolicy());
            Enumeration files = multi.getFileNames();
            while (files.hasMoreElements()) {
                String name = (String) files.nextElement();
                String filename = multi.getFilesystemName(name);
                String originalFilename = multi.getOriginalFileName(name);
                String type = multi.getContentType(name);
                File f = multi.getFile(name);
                ufc.file(null, f, filename, originalFilename, type);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void uploadMulti(UploadFileCall ufc) throws RuntimeException {
        uploadMulti("/upload", ufc);
    }

    /**
     * 多 文件上传
     *
     * @param path
     * @param ufc
     * @return
     * @throws Exception
     */
    public static void uploadMulti(String path, UploadFileCall ufc) throws RuntimeException {
        try {
            HttpServletRequest request = getReq();
            ServletContext servletContext = getServletContext();
            File file = new File(servletContext.getRealPath(path));
            if (!file.exists()) file.mkdir();
            DiskFileItemFactory fac = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(fac);
            upload.setHeaderEncoding("UTF-8");
            List<FileItem> fileItems = upload.parseRequest(request);
            for (FileItem item : fileItems) {
                if (!item.isFormField()) {
                    String name = item.getName();
                    String type = item.getContentType();
                    if (StringUtils.isNotBlank(name)) {
                        File f = new File(file + File.separator + name);
                        item.write(f);
                        ufc.file(null, f, name, name, type);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * jsonp方式
     * 多文件上传 + 进度条
     *
     * @param path
     * @param ufc
     * @return
     * @throws Exception
     */
    public static <T> T uploadMultiAndProgress(String path, Class<T> c, UploadFileNewCall ufc, final ProgressListener pl) throws RuntimeException {
        try {
            HttpServletRequest request = getReq();
//            final HttpSession session = getSession();
            ServletContext servletContext = getServletContext();
            File file = new File(servletContext.getRealPath(path));
            if (!file.exists()) file.mkdir();

            DiskFileItemFactory fac = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(fac);
            upload.setHeaderEncoding("UTF-8");
            upload.setProgressListener(pl);
            List<FileItem> fileItems = upload.parseRequest(request);

            T obj = c.newInstance();
            Field[] declaredFields = c.getDeclaredFields();
            for (FileItem item : fileItems) {
                if (!item.isFormField()) {

                    String name = item.getName();
                    String type = item.getContentType();
                    if (StringUtils.isNotBlank(name)) {
                        File f = new File(file + File.separator + name);
                        item.write(f);
                        ufc.file(obj, f, name, name, item.getSize(), type); // 贷出模式，处理真正的业务（可变的部分）
                    }
                } else {

                    String name = item.getFieldName();
                    // 循环表单值，封装到属性中,字段不多，所以效率还可以
                    for (Field field : declaredFields) {
                        field.setAccessible(true);
                        String fieldName = field.getName();
                        if (name.equals(fieldName)) {
                            String value = item.getString("UTF-8");
                            if (null == value) {
                                continue;
                            }
                            Class<?> type = field.getType();
                            if (type == Long.class) {
                                field.set(obj, Long.parseLong(value));
                            } else if (type == String.class) {
                                field.set(obj, value);
                            } else if (type == Byte.class) {
                                field.set(obj, Byte.parseByte(value));
                            } else if (type == Integer.class) {
                                field.set(obj, Integer.parseInt(value));
                            } else if (type == Character.class) {
                                field.set(obj, value.charAt(0));
                            } else if (type == Boolean.class) {
                                field.set(obj, Boolean.parseBoolean(value));
                            } else if (type == Double.class) {
                                field.set(obj, Double.parseDouble(value));
                            } else if (type == Float.class) {
                                field.set(obj, Float.parseFloat(value));
                            }
                        }
                    }
                }
            }
            return obj;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 封装表单提交数据到bean中<br/>
     * 仅仅支持一层的数据封装
     *
     * @param <T>
     * @throws ValidationException
     */
    public static <T> T packBean(Class<T> c) throws RuntimeException {
        try {
            HttpServletRequest request = ActionContext.getReq();
            T newInstance = c.newInstance();
            Field[] fields = c.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String name = field.getName();
                String value = request.getParameter(name);

                Class<?> type = field.getType();
                if (type.isArray()) {//暂时仅仅支持string 和  file数组
                    Class<?> componentType = type.getComponentType();
                    if (componentType == String.class) {
                        String[] values = request.getParameterValues(name);
//                    if (null == value || "".equals(value)) continue;
//                    String[] split = value.split(",");
                        field.set(newInstance, values);
                    } else if (componentType == File.class) {
                        ServletContext servletContext = getServletContext();
                        File file = new File(servletContext.getRealPath("upload"));
                        if (!file.exists())
                            file.mkdir();
                        MultipartRequest multi =
                                new MultipartRequest(request, file.getAbsolutePath(), MAX_POST_SIZE, "UTF-8", new DefaultFileRenamePolicy());
                        Enumeration files = multi.getFileNames();
                        List<File> fileList = new ArrayList<File>();
                        if (files.hasMoreElements()) {
                            File f = multi.getFile((String) files.nextElement());
                            fileList.add(f);
                        }
                        field.set(newInstance, fileList.toArray(new File[]{}));
                    }

                }
//            else if (type == File.class) {//单文件上传
//                ServletContext servletContext = getServletContext();
//                File file = new File(servletContext.getRealPath("upload"));
//                if (!file.exists())
//                    file.mkdir();
//                MultipartRequest multi =
//                        new MultipartRequest(request, file.getAbsolutePath(), 10 * 1024 * 1024, "UTF-8", new DefaultFileRenamePolicy());
//                Enumeration files = multi.getFileNames();
//                if (files.hasMoreElements()) {
//                    field.set(newInstance, multi.getFile((String) files.nextElement()));
//                }
//            }
                // 验证 切入
                validation(field, name, value);
                if (null == value || "".equals(value.trim())) {
                    continue;
                }
                if (type == Long.class) {
                    field.set(newInstance, Long.parseLong(value));
                } else if (type == String.class) {
                    field.set(newInstance, value);
                } else if (type == Byte.class) {
                    field.set(newInstance, Byte.parseByte(value));
                } else if (type == Integer.class) {
                    field.set(newInstance, Integer.parseInt(value));
                } else if (type == Character.class) {
                    field.set(newInstance, value.charAt(0));
                } else if (type == Boolean.class) {
                    field.set(newInstance, Boolean.parseBoolean(value));
                } else if (type == Double.class) {
                    field.set(newInstance, Double.parseDouble(value));
                } else if (type == Float.class) {
                    field.set(newInstance, Float.parseFloat(value));
                } else if (type == Date.class) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    field.set(newInstance, sdf.parse(value));
                }
            }
            return newInstance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 框架内部用的验证方法
     *
     * @param field
     * @param name
     * @param value
     * @throws ValidationException
     */
    private static void validation(Field field, String name, String value) throws RuntimeException {
        try {
            // 非空验证
            NotNull notNull = field.getAnnotation(NotNull.class);
            if (null != notNull) {
                if (null == value || "".equals(value)) {
                    throw new ValidationException(notNull.message());
                }
            }
            if (null == value)// 如果为空，则没必要经过验证，（因为notNull和Regexp有概念的冲突）
                return;
            // 正则验证
            Regexp regexp = field.getAnnotation(Regexp.class);
            if (null != regexp) {
                // String regEx = regexp.value();
                RegexpType regexpType = regexp.value();
                String regEx = regexpType.getName();
                Pattern pattern = Pattern.compile(regEx);
                Matcher matcher = pattern.matcher(value);
                if (!matcher.matches()) {
                    throw new ValidationException(regexp.message());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 封装表单提交数据到bean中<br/>
     * 支持多层数据的封装
     *
     * @param <T>
     * @param c
     * @return
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ValidationException
     */
    public static <T> T packBeanRecurrence(Class<T> c) throws RuntimeException {
        return pbRecurrence(c, "");
    }

    private static <T> T pbRecurrence(Class<T> c, String prefix) throws RuntimeException {
        try {
            HttpServletRequest request = ActionContext.getReq();
            T newInstance = c.newInstance();
            Field[] declaredFields = c.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                String name = field.getName();
                String key = "".equals(prefix) ? name : prefix + "." + name;
                String value = request.getParameter(key);

                Class<?> type = field.getType();
                if (type.isArray()) {//暂时仅仅支持string 和  file数组
                    Class<?> componentType = type.getComponentType();
                    if (componentType == String.class) {
                        String[] values = request.getParameterValues(name);
//                    if (null == value || "".equals(value)) continue;
//                    String[] split = value.split(",");
                        field.set(newInstance, values);
                    } else if (componentType == File.class) {
                        ServletContext servletContext = getServletContext();
                        File file = new File(servletContext.getRealPath("upload"));
                        if (!file.exists())
                            file.mkdir();
                        MultipartRequest multi =
                                new MultipartRequest(request, file.getAbsolutePath(), 1024 * 1024 * 1024, "UTF-8", new DefaultFileRenamePolicy());
                        Enumeration files = multi.getFileNames();
                        List<File> fileList = new ArrayList<File>();
                        if (files.hasMoreElements()) {
                            File f = multi.getFile((String) files.nextElement());
                            fileList.add(f);
                        }
                        field.set(newInstance, fileList.toArray(new File[]{}));
                    }

                }
                // 验证 切入
                validation(field, name, value);
                if (null == value) {
                    continue;
                }
                if (type == Long.class) {
                    field.set(newInstance, Long.parseLong(value));
                } else if (type == String.class) {
                    field.set(newInstance, value);
                } else if (type == Byte.class) {
                    field.set(newInstance, Byte.parseByte(value));
                } else if (type == Integer.class) {
                    field.set(newInstance, Integer.parseInt(value));
                } else if (type == Character.class) {
                    field.set(newInstance, value.charAt(0));
                } else if (type == Boolean.class) {
                    field.set(newInstance, Boolean.parseBoolean(value));
                } else if (type == Double.class) {
                    field.set(newInstance, Double.parseDouble(value));
                } else if (type == Float.class) {
                    field.set(newInstance, Float.parseFloat(value));
                } else if (type == Date.class) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    field.set(newInstance, sdf.parse(value));
                } else { // 引用类型？
                    Object obj = pbRecurrence(field.getType(), name);// 递归
                    field.set(newInstance, obj);
                }
            }
            return newInstance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void callCtl(String class_method) throws RuntimeException {
        try {
            HttpServletRequest req = ActionContext.getReq();
            HttpServletResponse resp = ActionContext.getResp();
            if (StringUtils.isNotBlank(class_method)) {
                Cache cache = SshConstant.CACHE_MAP.get(class_method);
                if (null != cache && !SshConstant.DEBUG_MODE) {
                    // long begin = System.currentTimeMillis();
                    cache.getMethod().invoke(cache.getObject(), req, resp);
                    // long end = System.currentTimeMillis();
                    // //System.out.println("走缓存了：" + (end - begin));
                } else {
                    // long begin = System.currentTimeMillis();
                    int lastIndex = class_method.lastIndexOf(".");
                    String clazzTemp = class_method.substring(0, lastIndex);
                    // 没有包，加上包路径
                    Class<?> ctlClazz = null;
                    for (String packageName : SshConstant.PACKAGE_NAME) {
                        try {
                            String clazz = (clazzTemp.indexOf(".") == clazzTemp.lastIndexOf(".")) ? (packageName + "." + clazzTemp) : clazzTemp;
                            ctlClazz = Class.forName(clazz);
                        } catch (Exception e) {
                        }
                        if (ctlClazz != null) break;
                    }
                    Object ctlObj = ctlClazz.newInstance();
                    String method = class_method.substring(++lastIndex);
                    // Method ctlMet = ctlClazz.getMethod(method,
                    // HttpServletRequest.class, HttpServletResponse.class);
                    Method ctlMet = ctlClazz.getMethod(method);
                    SshConstant.CACHE_MAP.put(class_method, new Cache(ctlObj, ctlMet));// put
                    // cache

                    // ctlMet.invoke(ctlObj, req, resp);
                    ctlMet.invoke(ctlObj);
                    // long end = System.currentTimeMillis();
                    // //System.out.println("没走缓存：" + (end - begin));
                }
            } else {
                ServletOutputStream out = resp.getOutputStream();
                out.print("error");
                out.flush();
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public static void callCtl(Class clazz, String method) throws RuntimeException {
        callCtl(clazz.getSimpleName() + "." + method);
    }

    public static ServletContext getServletContext() {
        return getSession().getServletContext();
    }

    public static HttpSession getSession() {
        return ActionContext.getSession();
    }

    public static HttpServletRequest getReq() {
        return ActionContext.getReq();
    }

    public static HttpServletResponse getResp() {
        return ActionContext.getResp();
    }

    public static byte[] toByteArray(String path) throws RuntimeException {
        try {
            return IOUtils.toByteArray(new FileInputStream(WebUtil.getSession().getServletContext().getRealPath("/") + File.separator + path));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getStringTrim(String... key) throws RuntimeException {
        try {
            try {
                HttpServletRequest req = WebUtil.getReq();
                String p = req.getParameter(key[0]);
                if (null != p)
                    return p.trim();
                return p;
            } catch (Exception e) {
                if (key.length == 2)
                    throw new ValidationException(key[1]);
                throw new ValidationException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getString(String... key) throws RuntimeException {
        try {
            try {
                HttpServletRequest req = WebUtil.getReq();
                return req.getParameter(key[0]);
            } catch (Exception e) {
                if (key.length == 2)
                    throw new ValidationException(key[1]);
                throw new ValidationException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer getInteger(String... key) throws RuntimeException {
        try {
            try {
                HttpServletRequest req = WebUtil.getReq();
                String attachId = req.getParameter(key[0]);
                return Integer.parseInt(attachId);
            } catch (Exception e) {
                if (key.length == 2)
                    throw new ValidationException(key[1]);
                throw new ValidationException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Long getLong(String... key) throws RuntimeException {
        try {
            try {
                HttpServletRequest req = WebUtil.getReq();
                String attachId = req.getParameter(key[0]);
                return Long.parseLong(attachId);
            } catch (Exception e) {
                if (key.length == 2)
                    throw new ValidationException(key[1]);
                throw new ValidationException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Byte getByte(String... key) throws RuntimeException {
        try {
            try {
                HttpServletRequest req = WebUtil.getReq();
                String attachId = req.getParameter(key[0]);
                return Byte.parseByte(attachId);
            } catch (Exception e) {
                if (key.length == 2)
                    throw new ValidationException(key[1]);
                throw new ValidationException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Short getShort(String... key) throws RuntimeException {
        try {
            try {
                HttpServletRequest req = WebUtil.getReq();
                String attachId = req.getParameter(key[0]);
                return Short.parseShort(attachId);
            } catch (Exception e) {
                if (key.length == 2)
                    throw new ValidationException(key[1]);
                throw new ValidationException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Boolean getBoolean(String... key) throws RuntimeException {
        try {
            try {
                HttpServletRequest req = WebUtil.getReq();
                String attachId = req.getParameter(key[0]);
                return Boolean.parseBoolean(attachId);
            } catch (Exception e) {
                if (key.length == 2)
                    throw new ValidationException(key[1]);
                throw new ValidationException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Double getDouble(String... key) throws RuntimeException {
        try {
            try {
                HttpServletRequest req = WebUtil.getReq();
                String attachId = req.getParameter(key[0]);
                return Double.parseDouble(attachId);
            } catch (Exception e) {
                if (key.length == 2)
                    throw new ValidationException(key[1]);
                throw new ValidationException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Float getFloat(String... key) throws RuntimeException {
        try {
            try {
                HttpServletRequest req = WebUtil.getReq();
                String attachId = req.getParameter(key[0]);
                return Float.parseFloat(attachId);
            } catch (Exception e) {
                if (key.length == 2)
                    throw new ValidationException(key[1]);
                throw new ValidationException(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @param path 验证失败跳转的页面
     * @return 验证通过返回true, 否则返回false
     * @throws ServletException
     * @throws IOException
     */
    public static boolean checkValidateCode(String path) throws RuntimeException {
        try {
            HttpServletRequest req = WebUtil.getReq();
            HttpSession session = WebUtil.getSession();
            String isAutoLogin = req.getParameter("isAutoLogin");
            String uInputAuthCode = req.getParameter("txtAuthCode");
            String uAuthCode = (String) session.getAttribute("ValidCode");
            if (!"true".equals(isAutoLogin)) {
                if (StringUtils.isBlank(uInputAuthCode)) {
                    req.setAttribute("errorMsg", "验证码不能为空");
                    WebUtil.forward(path);
                    return false;
                } else if (!StringUtils.isBlank(uAuthCode)) {
                    if (!uInputAuthCode.equalsIgnoreCase(uAuthCode)) {
                        req.setAttribute("errorMsg", "验证码不匹配");
                        WebUtil.forward(path);
                        return false;
                    }
                }
            }
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取order asc或者desc
     *
     * @return
     * @throws ValidationException
     */
    public static String getOrder() throws RuntimeException {
        String order = WebUtil.getString("sSortDir_0");
        return order;
    }

    /**
     * 获取要排序的字段
     *
     * @return
     * @throws ValidationException
     */
    public static String getSort() throws RuntimeException {
        String sort = WebUtil.getString("iSortCol_0");
        if (StringUtils.isBlank(sort)) {
            return null;
        }
        String s = WebUtil.getString("mDataProp_" + sort);
        return s;
    }


    /**
     * datatable 预先 处理
     */
    public static void prePageForDataTable() throws RuntimeException {
        if (null != getReq()) { // servelt环境
            int pageSize = getInteger("iDisplayLength");
            int pageNum = (getInteger("iDisplayStart") / pageSize) + 1;
            String order = getOrder();
            String sortColumn = getSort();

            PageHelper.startPage(pageNum, pageSize);
        } else { // 如果是通过 测试框架等非servlet环境下调用，则应用默认值
            PageHelper.startPage(1, 10);
        }
    }


}
