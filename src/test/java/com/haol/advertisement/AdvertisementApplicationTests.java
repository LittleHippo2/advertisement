package com.haol.advertisement;

import com.haol.advertisement.entity.QRCode;
import com.haol.advertisement.mapper.CountMapper;
import com.haol.advertisement.service.ErWeiMaService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
class AdvertisementApplicationTests {

	@Autowired
	private CountMapper countMapper;

	@Test
	void contextLoads() {
		QRCode qrCode = new QRCode();
		String uuid = UUID.randomUUID().toString().replaceAll("-","");
		qrCode.setId(uuid);
		qrCode.setCount(0);
		qrCode.setName("百度");
		qrCode.setUrl("www.baidu.com");
		qrCode.setPath("D:\\a\\b\\c.jpg");

		countMapper.insert(qrCode);

	}

}
