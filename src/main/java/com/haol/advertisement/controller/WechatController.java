package com.haol.advertisement.controller;

import com.haol.advertisement.service.EntranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class WechatController {

    @Autowired
    private EntranceService entranceService;

    @GetMapping(value = "/wechat/rk", produces = "text/plain;charset=utf-8")
    public String wechat(@RequestParam(name = "signature")String signature,
                         @RequestParam(name = "timestamp")Long timestamp,
                         @RequestParam(name = "nonce")String nonce,
                         @RequestParam(name = "echostr")String echostr,
                         HttpServletResponse response) throws IOException {
        if (entranceService.weChatVerification(signature, timestamp, nonce, echostr)){
            response.getOutputStream().println(echostr);

        }
        return "";
    }
}
