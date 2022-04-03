package com.weat.weat.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
@DependsOn("encoder")
public class Bootstrap implements InitializingBean {

	@Autowired
	private BootstrapService bootStrapService;

	@Override
	public void afterPropertiesSet() throws Exception {
		bootStrapService.execute();
	}
}
