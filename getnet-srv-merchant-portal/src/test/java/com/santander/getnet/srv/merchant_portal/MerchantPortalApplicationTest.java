package com.santander.getnet.srv.merchant_portal;

import com.santander.getnet.srv.merchant_portal.service.NuekService;
import com.santander.getnet.srv.merchant_portal.service.impl.NuekServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MerchantPortalApplicationTest {

	@Autowired
	NuekService nuekService;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(nuekService);
		Assertions.assertInstanceOf(NuekServiceImpl.class, nuekService);
	}

}
