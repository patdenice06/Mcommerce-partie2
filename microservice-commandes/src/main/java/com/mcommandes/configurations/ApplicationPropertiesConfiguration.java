package com.mcommandes.configurations;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mes-configs") 
@RefreshScope
public class ApplicationPropertiesConfiguration {

	private int limiteDeCommandes;

	public ApplicationPropertiesConfiguration() {}

	public int getLimiteDeCommandes() {
		return limiteDeCommandes;
	}

	public void setLimiteDeCommandes(int limiteDeCommandes) {
		this.limiteDeCommandes = limiteDeCommandes;
	}
	
	
	
	
	
}
