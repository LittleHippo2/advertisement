package com.haol.advertisement.tools;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

public class FileDownLoad {

    public static HttpServletResponse download(String path, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), "UTF-8"));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return response;
    }

    //path 为图片在服务器的绝对路径
    public static void getPhoto(HttpServletResponse response, String path) throws Exception {
        File file = new File(path);
        FileInputStream fis;
        fis = new FileInputStream(file);

        long size = file.length();
        byte[] temp = new byte[(int) size];
        fis.read(temp, 0, (int) size);
        fis.close();
        byte[] data = temp;
        response.setContentType("image/jpg");
        OutputStream out = response.getOutputStream();
        out.write(data);
        out.flush();
        out.close();

    }

    public static void downFile( HttpServletResponse response, String path) {
        try {
            String fileName = "";
            if (path.contains("\\")){
                fileName = path.substring(path.lastIndexOf("\\")+1);
            }else if (path.contains("/")){
                fileName = path.substring(path.lastIndexOf("/")+1);
            }
            // 得到要下载的文件
            File file = new File(path);
            // 如果文件不存在
            if (!file.exists()) {
                System.out.println("您要下载的资源已被删除！！");
                return;
            }
        // 设置响应头，控制浏览器下载该文件
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        // 读取要下载的文件，保存到文件输入流
            FileInputStream in = new FileInputStream(file);
        // 创建输出流
            OutputStream out = response.getOutputStream();
        // 创建缓冲区
            byte buffer[] = new byte[1024];
            int len = 0;
        // 循环将输入流中的内容读取到缓冲区当中
            while ((len = in.read(buffer)) > 0) {
        // 输出缓冲区的内容到浏览器，实现文件下载
                out.write(buffer, 0, len);
            }
        // 关闭文件输入流
            in.close();
        // 关闭输出流
            out.close();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        String path = "C:\\a\\b.jpg";
        String fileName = path.substring(path.lastIndexOf("\\")+1);
        System.out.println(fileName);
    }
}
