package com.alloy.cloud.auth;

import com.alloy.cloud.common.security.annotation.EnableCloudFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringCloudApplication
@EnableCloudFeignClients
@EnableCaching
@ComponentScan("com.alloy.cloud")
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}

}
