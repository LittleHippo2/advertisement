package com.haol.advertisement.controller.route;


import com.haol.advertisement.service.ErWeiMaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Base64;

@Controller
@Slf4j
public class RouteController {

    @Autowired
    private ErWeiMaService erWeiMaService;

    /**
     * 跳转到登录页面
     */
    @RequestMapping("/login")
    public String toLoginPage() {
        return "loginpage/login";
    }

    @RequestMapping("/to_page")
    public String toPage() {
        return "getErWeiMa";
    }

    @RequestMapping("/num")
    public void getNum() {
    }

    @RequestMapping("/jump_url")
    public String jumpUrl(@RequestParam(name = "url") String url, @RequestParam(name = "uuid") String uuid) {
        byte[] bytes = Base64.getDecoder().decode(url);
        url = new String(bytes);
        log.info("广告地址为：" + url);
        log.info("uuid为：" + uuid);
        int a = erWeiMaService.countVisits(uuid);
        if (a != 0) {
            return "redirect:" + url;
        } else {
            return "失败";
        }
    }
}
