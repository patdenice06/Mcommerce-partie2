package com.clientui.proxies;

import com.clientui.beans.PaiementBean;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-paiement", url = "localhost:9003")
public interface MicroservicePaiementProxy {

    @PostMapping(value = "/paiement")
    ResponseEntity<PaiementBean> payerUneCommande(@RequestBody PaiementBean paiement);
    
    @GetMapping(value = "/liste-paiements")
	List<PaiementBean> listeDesPaiements();

}
