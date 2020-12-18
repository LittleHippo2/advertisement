package com.haol.advertisement.tools;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *  常见的浏览器格式为
 *  ext/plain 　　　　　　　　文本类型
 *  text/css  　　　　　　　　 css类型
 *  text/html 　　 　　　　　　html类型
 *  application/x-javascript 　　js类型
 *  application/json　　　　　 json类型
 *  image/png jpg gif　　　　　 image/*
 */
public class DownLoadFile {

    /**
     * 根据URL 和网页类型生成需要保存的网页的文件名，去除URL中的非文件名字符
     * @param url
     * @param contentType
     * @return
     */
    public static String getFileNameByUrl(String url, String contentType){
        //移除http
        url = url.substring(7);
        //text/html类型
        if(contentType.indexOf("html") != -1){
            url = url.replaceAll("[\\?/:*|<>\"]", "_")+".html";
            return url;
        }else{
            //如application/pdf类型
            return url.replaceAll("[\\?/:*|<>\"]", "_")+"."+contentType.substring(contentType.lastIndexOf("/")+1);
        }
    }

    /**
     * 保存网页字节数据到本地文件，filepath 为要保存的文件的相对路径
     */
    public static void saveToLOcal(byte[] data, String failPath){
        try{
            DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(failPath)));
            for (int i = 0; i < data.length; i++)
                out.write(data[i]);
            out.flush();
            out.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //下载URl指向的网页
    public static String downLoadFild(String url){
        String filePath = null;
        RequestConfig requestConfig = RequestConfig.custom()
                //设置请求超时时间5s
                .setConnectionRequestTimeout(5000)
                //设置连接超时时间5s
                .setConnectTimeout(5000)
                //设置读写超时时间5s
                .setSocketTimeout(5000)
                .build();
        //创建一个客户端，类似打开一个浏览器,设置请求失败重试，模式是3次
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setRetryHandler(new DefaultHttpRequestRetryHandler(3, true))
                .build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        HttpEntity responseEntity = null;
        try{
            HttpResponse response = httpClient.execute(httpGet);

            int code = response.getStatusLine().getStatusCode();

            if (200 != code){
                System.out.println("Method Failed:" + response.getStatusLine());
                filePath = null;
            }

            // 处理 HTTP 响应内容
            responseEntity = response.getEntity();
            byte[] data = EntityUtils.toString(responseEntity, "UTF8").getBytes();

            //获取content-type
            String contentType = responseEntity.getContentType().toString();
            System.out.println(contentType);

            filePath ="E:\\spider\\"+getFileNameByUrl(url, contentType);
            saveToLOcal(data, filePath);

            return filePath;
        }catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            httpGet.releaseConnection();
        }
        return null;
    }




}
