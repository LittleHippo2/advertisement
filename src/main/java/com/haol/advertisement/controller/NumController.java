package com.haol.advertisement.controller;

import com.haol.advertisement.service.ErWeiMaService;
import com.haol.advertisement.tools.ErWeiMa;
import com.haol.advertisement.tools.FileDownLoad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@Controller
@Slf4j
public class NumController {

    @Autowired
    private ErWeiMa erWeiMa;

    @Autowired
    private ErWeiMaService erWeiMaService;

    @RequestMapping("/to_page")
    public String toPage(){
        return "getErWeiMa";
    }

    @RequestMapping("/num")
    public void getNum(){
    }

    /**
     * 生成二维码
     * @param url
     * @param ggname
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/getEr")
    @ResponseBody
    public String getErWeiMa(@RequestParam(name = "url")String url,
                             @RequestParam(name = "ggname")String ggname,
                             HttpServletResponse response) throws Exception {
        if(!url.contains("http://") && !url.contains("https://")){
            url = "http://" + url;
        }
        String path = erWeiMaService.getErWeiMa(url, ggname, 300, 300);

        return path;
    }

    /**
     * 下载二维码
     * @param path
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/filedownload")
    @ResponseBody
    public String fileDownLoad(@RequestParam(name = "path")String path, HttpServletResponse response) throws Exception {
        FileDownLoad.getPhoto(response, path);
        return "";
    }

    @RequestMapping("/jump_url")
    public String jumpUrl(@RequestParam(name = "url")String url,  @RequestParam(name = "uuid")String uuid){
        byte[] bytes = Base64.getDecoder().decode(url);
        url = new String(bytes);
        log.info("广告地址为："+url);
        log.info("uuid为："+uuid);
        int a = erWeiMaService.countVisits(uuid);
        if (a !=0 ){
            return "redirect:"+url;
        }else{
            return "失败";
        }

    }







}
