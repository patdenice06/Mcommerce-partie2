package com.clientui.proxies;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.clientui.beans.ProductBean;

@FeignClient(name = "microservice-produits")
@RibbonClient(name = "microservice-produits")
public interface MicroserviceProduitsProxy {

	@GetMapping(value = "/Produits")
	List<ProductBean> listeDesProduits();

	@GetMapping( value = "/Produits/{id}")
	ProductBean recupererUnProduit(@PathVariable("id") int id);
	
}
