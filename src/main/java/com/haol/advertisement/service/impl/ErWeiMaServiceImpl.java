package com.haol.advertisement.service.impl;

import com.google.zxing.WriterException;
import com.haol.advertisement.entity.QRCode;
import com.haol.advertisement.mapper.CountMapper;
import com.haol.advertisement.service.ErWeiMaService;
import com.haol.advertisement.tools.ErWeiMa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
@Slf4j
public class ErWeiMaServiceImpl implements ErWeiMaService{

    @Value("${server.port}")
    private String port;

    @Value("${server.ip}")
    private String ip;

    @Autowired
    private CountMapper countMapper;

    @Override
    public String getErWeiMa(String url, String ggname, int width, int height) throws IOException, WriterException {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        //获取服务器ip
//        String ip = GetIp.getIp();
        log.info("服务器ip为："+ ip);
        //拼接二维码地址
        byte[] bytes = url.getBytes();
        String base64Url = Base64.getEncoder().encodeToString(bytes);
        String er_url = "http://" + ip + ":"+ port + "/jump_url?url="+base64Url+"&uuid="+uuid;
        log.info("二维码地址为："+er_url);
        String path = ErWeiMa.getErWeiMa(er_url, 300, 300, ggname);
        log.info("图片保存地址为："+path);
        QRCode qrCode = new QRCode();
        qrCode.setId(uuid);
        qrCode.setCount(0);
        qrCode.setName(ggname);
        qrCode.setUrl(url);
        qrCode.setPath(path);

        countMapper.insert(qrCode);

        return path;
    }

    @Override
    public int countVisits(String uuid) {
        QRCode qrCode1 = countMapper.selectById(uuid);

        QRCode qrCode = new QRCode();
        qrCode.setId( uuid);
        qrCode.setCount(qrCode1.getCount()+1);

        return countMapper.updateById(qrCode);
    }

}
