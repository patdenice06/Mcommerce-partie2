package com.mproduits.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-configs")
@RefreshScope
public class ApplicationPropertiesConfiguration {

	private int limiteDeProduits;

	public ApplicationPropertiesConfiguration() {
	}

	public int getLimiteDeProduits() {
		return limiteDeProduits;
	}

	public void setLimiteDeProduits(int limiteDeProduits) {
		this.limiteDeProduits = limiteDeProduits;
	}
	
}
