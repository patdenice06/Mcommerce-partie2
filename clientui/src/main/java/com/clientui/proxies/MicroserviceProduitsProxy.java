package com.clientui.proxies;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.clientui.beans.ProductBean;

//@FeignClient(name = "microservice-produits")
@FeignClient(name = "zuul-server")
@RibbonClient(name = "microservice-produits")
public interface MicroserviceProduitsProxy {

/*	
	@GetMapping(value = "/Produits")
	List<ProductBean> listeDesProduits();
	
	@GetMapping( value = "/Produits/{id}")
	ProductBean recupererUnProduit(@PathVariable("id") int id);
*/
	/*
	 * On ajoute /microservice-produits/ devant toutes les URI.  Feign ira alors contacter ZUUL avec l'URI "/microservice-produits/Produits" ou
	 * "/microservice-produits/Produits/{id}. Comme ZUUL extrait alors le nom du Microservice concerné de l'URL "microservice-produits",
	 * il pourra rediriger la requête vers la destination voulue.
	 */
    @GetMapping(value = "/microservice-produits/Produits")
    List<ProductBean> listeDesProduits();	
	

    @GetMapping( value = "/microservice-produits/Produits/{id}")
    ProductBean recupererUnProduit(@PathVariable("id") int id);
	
}
