package com.haol.advertisement.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
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

    private int state;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
}
