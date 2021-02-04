package com.clientui.proxies;

import com.clientui.beans.PaiementBean;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "microservice-paiement")
@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-paiement")
public interface MicroservicePaiementProxy {

/*	
    @PostMapping(value = "/paiement")
    ResponseEntity<PaiementBean> payerUneCommande(@RequestBody PaiementBean paiement);
    
    @GetMapping(value = "/liste-paiements")
	List<PaiementBean> listeDesPaiements();
    */
	
	/*
	 * Ajout de "microservice-paiement" devant toutes les URI de manière à ce que Feign contacte ZUUL qui redirigera les requêtes
	 * vers la destination voulue
	 */
    @PostMapping(value = "microservice-paiement/paiement")
    ResponseEntity<PaiementBean> payerUneCommande(@RequestBody PaiementBean paiement);
    
    @GetMapping(value = "microservice-paiement/liste-paiements")
	List<PaiementBean> listeDesPaiements();


}
