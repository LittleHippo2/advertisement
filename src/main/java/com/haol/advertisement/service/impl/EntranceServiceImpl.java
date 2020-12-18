package com.haol.advertisement.service.impl;

import com.haol.advertisement.service.EntranceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 微信入口
 * 开发者通过检验signature对请求进行校验（下面有校验方式）。若确认此次GET请求来自微信服务器，请原样返回echostr参数内容，则接入生效，成为开发者成功，否则接入失败。加密/校验流程如下：
 * 1）将token、timestamp、nonce三个参数进行字典序排序 2）将三个参数字符串拼接成一个字符串进行sha1加密 3）开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
 *
 */
@Service
@Slf4j
public class EntranceServiceImpl implements EntranceService {

    @Value("${param.token}")
    private String token;

    @Override
    public Boolean weChatVerification(String signature, Long timestamp, String nonce, String echostr) {

        log.info("获取到的signature："+ signature);
        log.info("获取到的timestamp："+ timestamp);
        log.info("获取到的nonce："+ nonce);
        log.info("获取到的echostr："+ echostr);

        //字典排序
        List<Object> list = new ArrayList<>();
        list.add(token);
        list.add(timestamp);
        list.add(nonce);

        Collections.sort(list, new SpellComparator());
        String str = "";
        for(Object o : list){
            str += o.toString();
        }
        str = DigestUtils.sha1Hex(str.getBytes());
        log.info("sha1加密过后的数据为："+ str);
        if (str.equals(signature)){
            return true;
        }
        return false;
    }

    class SpellComparator implements Comparator {
        public int compare(Object o1, Object o2) {
            try {
                // 取得比较对象的汉字编码，并将其转换成字符串
                String s1 = new String(o1.toString().getBytes("GB2312"), "ISO-8859-1");
                String s2 = new String(o2.toString().getBytes("GB2312"), "ISO-8859-1");
                // 运用String类的 compareTo（）方法对两对象进行比较
                return s1.compareTo(s2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 0;
        }
    }
}
