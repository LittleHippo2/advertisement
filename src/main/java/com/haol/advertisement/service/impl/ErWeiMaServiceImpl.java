package com.haol.advertisement.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.zxing.WriterException;
import com.haol.advertisement.entity.QRCode;
import com.haol.advertisement.mapper.CountMapper;
import com.haol.advertisement.service.ErWeiMaService;
import com.haol.advertisement.tools.ErWeiMa;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

@Service
@Slf4j
@Transactional
public class ErWeiMaServiceImpl implements ErWeiMaService{

    @Value("${server.port}")
    private String port;

    @Value("${server.ip}")
    private String ip;

    @Value("${param.width}")
    private int width;

    @Value("${param.height}")
    private int height;

    @Autowired
    private CountMapper countMapper;

    @Override
    public String getErWeiMa(String url, String ggname) throws IOException, WriterException {
        String uuid = UUID.randomUUID().toString().replaceAll("-","");
        //获取服务器ip
//        String ip = GetIp.getIp();
        log.info("服务器ip为："+ ip);
        //拼接二维码地址
        byte[] bytes = url.getBytes();
        String base64Url = Base64.getEncoder().encodeToString(bytes);
        String er_url = "http://" + ip + ":"+ port + "/jump_url?url="+base64Url+"&uuid="+uuid;
        log.info("二维码地址为："+er_url);
        String path = ErWeiMa.getErWeiMa(er_url, width, height, ggname);
        log.info("图片保存地址为："+path);
        QRCode qrCode = new QRCode();
        qrCode.setId(uuid);
        qrCode.setCount(0);
        qrCode.setName(ggname);
        qrCode.setUrl(url);
        qrCode.setPath(path);
        qrCode.setVersion(0);

        countMapper.insert(qrCode);

        return path;
    }

    @Override
    public int countVisits(String uuid) {
        QRCode qrCode = countMapper.selectById(uuid);
        qrCode.setCount(qrCode.getCount()+1);
        return countMapper.updateById(qrCode);
    }

    @Override
    public IPage<QRCode> selectUserPage(Page<QRCode> page, Integer state) {
        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
        // page.setOptimizeCountSql(false);
        // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
        // 要点!! 分页返回的对象与传入的对象是同一个
        return countMapper.selectPageQRCode(page, state);
    }

    public void deleteObjectById(String id){
        QRCode qrCode = new QRCode();
        qrCode.setId(id);
        qrCode.setState(1);
//        QRCode q = countMapper.selectById(id);
//        File file = new File(q.getPath());
//        if (file.exists()){
//            file.delete();
//        }
//        countMapper.deleteById(id);
        countMapper.updateById(qrCode);
    }

}
