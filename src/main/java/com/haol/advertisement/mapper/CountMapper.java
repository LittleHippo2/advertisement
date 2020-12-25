package com.haol.advertisement.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haol.advertisement.entity.QRCode;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CountMapper extends BaseMapper<QRCode> {

    IPage<QRCode> selectPageQRCode(Page<?> page, Integer state);
}
