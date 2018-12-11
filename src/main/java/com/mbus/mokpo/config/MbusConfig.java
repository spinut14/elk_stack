package com.mbus.mokpo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MbusConfig {

	@Bean
	public MbusConfigInfo getConfigInfo () {
		MbusConfigInfo configInfo = new MbusConfigInfo();
		return configInfo;
	}

}
