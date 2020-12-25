package com.haol.advertisement.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haol.advertisement.entity.HttpResponse;
import com.haol.advertisement.entity.QRCode;
import com.haol.advertisement.service.ErWeiMaService;
import com.haol.advertisement.tools.FileDownLoad;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Base64;

@RestController
@Slf4j
public class NumController {

    @Autowired
    private ErWeiMaService erWeiMaService;

    /**
     * 生成二维码
     * @param url
     * @param ggname
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/getEr")
    public String getErWeiMa(@RequestParam(name = "url")String url,
                             @RequestParam(name = "ggname")String ggname,
                             HttpServletResponse response) throws Exception {
        if(!url.contains("http://") && !url.contains("https://")){
            url = "http://" + url;
        }
        String path = erWeiMaService.getErWeiMa(url, ggname);
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
    public String fileDownLoad(@RequestParam(name = "path")String path, HttpServletResponse response){
        FileDownLoad.downFile(response, path);
        return "";
    }

    @RequestMapping("/get_count")
    public HttpResponse getDataPage(@RequestParam(name = "page", defaultValue = "1")Integer page,
                                    @RequestParam(name = "limit", defaultValue = "10")Integer size,
                                    @RequestParam(name = "url", required = false)String url){
        Page page1 = new Page(page, size);
        IPage<QRCode> data = erWeiMaService.selectUserPage(page1, 0);

        return new HttpResponse(page, size, Math.toIntExact(data.getTotal()), data.getRecords(), "成功", 200);
    }

    @RequestMapping("/delete_url")
    public HttpResponse deleteDate(@RequestParam(name = "uuid")String id){

        erWeiMaService.deleteObjectById(id);
        return new HttpResponse("成功", 200);
    }





}
