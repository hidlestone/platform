package com.fallframework.platform.starter.wechatwork.service.token;

import com.fallframework.platform.starter.wechatwork.WXWorkApplication;
import com.fallframework.platform.starter.wechatwork.dto.GetAccessTokenDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author zhuangpf
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WXWorkApplication.class)
public class TokenServiceTest {

	@Autowired
	private TokenService tokenService;

	@Test
	public void getTokenTest() throws IOException {
		GetAccessTokenDto dto = new GetAccessTokenDto();
		dto.setCorpId("ww372907d5406d4a59");
		dto.setSecret("uKCXBRISNHyQgxLjZYE1NCx49zNR7CAqqVWAft3R9rA");
		tokenService.getToken(dto);
	}
	
}
