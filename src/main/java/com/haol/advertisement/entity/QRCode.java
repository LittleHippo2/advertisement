package com.haol.advertisement.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 二维码表
 */
@Data
@TableName(value = "qr_code")
public class QRCode {

    private String id;

    private int count;

    private String name;

    private String url;

    private String path;
}
