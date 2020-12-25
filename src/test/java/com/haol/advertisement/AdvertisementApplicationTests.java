package com.haol.advertisement;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haol.advertisement.entity.QRCode;
import com.haol.advertisement.mapper.CountMapper;
import com.haol.advertisement.service.ErWeiMaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
class AdvertisementApplicationTests {

	@Autowired
	private CountMapper countMapper;
	@Autowired
	private ErWeiMaService erWeiMaService;

	@Test
	void contextLoads() {
		Page p = new Page(1,10);
		IPage<QRCode> q =  erWeiMaService.selectUserPage(p, 0);
		List<QRCode> b = q.getRecords();
		System.out.println(q.getTotal());
		System.out.println(b.size());
	}

	@Test
	void test(){
		QRCode qrCode = countMapper.selectById("b559c93d936642b79355f3fdcf55f554");
		qrCode.setCount(10);
		countMapper.updateById(qrCode);

	}

}
