package com.haol.advertisement.service;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface ErWeiMaService {

   String getErWeiMa(String url, String ggname, int width, int height) throws IOException, WriterException;

   int countVisits(String uuid);

}
