package com.haol.advertisement.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.zxing.WriterException;
import com.haol.advertisement.entity.QRCode;

import java.io.IOException;

public interface ErWeiMaService {

   String getErWeiMa(String url, String ggname) throws IOException, WriterException;

   int countVisits(String uuid);

   IPage<QRCode> selectUserPage(Page<QRCode> page, Integer state);

   void deleteObjectById(String id);



}
