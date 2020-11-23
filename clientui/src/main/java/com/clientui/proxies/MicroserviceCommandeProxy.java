package com.clientui.proxies;

import com.clientui.beans.CommandeBean;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-commandes")
@RibbonClient(name = "microservice-commandes")

public interface MicroserviceCommandeProxy {

    @PostMapping(value = "/commandes")
    CommandeBean ajouterCommande(@RequestBody CommandeBean commande);
    
    @GetMapping(value = "/commandes/{id}")
    Optional<CommandeBean> recupererUneCommande(@PathVariable("id") int id);

    @GetMapping(value = "/liste-commandes")
	List<CommandeBean> listeDesCommandes();
    
}
