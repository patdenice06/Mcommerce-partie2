package com.mpaiement.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-configs")
@RefreshScope
public class ApplicationPropertiesConfiguration {
	
	private int limiteDePaiement;

	public ApplicationPropertiesConfiguration() {}

	public int getLimiteDePaiement() {
		return limiteDePaiement;
	}

	public void setLimiteDePaiement(int limiteDePaiement) {
		this.limiteDePaiement = limiteDePaiement;
	}
	
	
	
	
}
