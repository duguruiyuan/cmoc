package com.xuequ.cmoc.common;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;


public class AppConfigurer extends PropertyPlaceholderConfigurer{
	
	public static final Log log = LogFactory.getLog(AppConfigurer.class);

	@Override
	protected Properties mergeProperties() throws IOException {
		Properties superProps = super.mergeProperties();
		superProps.setProperty("env", Configuration.getInstance().getEnv());
		log.info("运行环境====>>" + Configuration.getInstance().getEnv());
		return superProps;
	}
}
