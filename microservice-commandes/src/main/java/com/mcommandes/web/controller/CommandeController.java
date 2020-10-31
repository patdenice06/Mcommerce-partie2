package com.mcommandes.web.controller;


import com.mcommandes.configurations.ApplicationPropertiesConfiguration;
import com.mcommandes.dao.CommandesDao;
import com.mcommandes.model.Commande;
import com.mcommandes.web.exceptions.CommandeNotFoundException;
import com.mcommandes.web.exceptions.ImpossibleAjouterCommandeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CommandeController {

    @Autowired
    CommandesDao commandesDao;
    
    @Autowired
    ApplicationPropertiesConfiguration appProperties;
    
    // Affiche la liste de toutes les commandes disponibles
    @GetMapping(value = "/liste-commandes")
    public List<Commande> listeDesCommandes(){
        List<Commande> commandes = commandesDao.findAll();
        if(commandes.isEmpty()) throw new CommandeNotFoundException("Aucune commande");
        return commandes;
    }

    @PostMapping (value = "/commandes")
    public ResponseEntity<Commande> ajouterCommande(@RequestBody Commande commande){

        Commande nouvelleCommande = commandesDao.save(commande);
        
        // DEBUG
        System.out.println( "limiteDeCommandes = "+ appProperties.getLimiteDeCommandes() );

        if(nouvelleCommande == null) throw new ImpossibleAjouterCommandeException("Impossible d'ajouter cette commande");

        return new ResponseEntity<Commande>(commande, HttpStatus.CREATED);
    }

    @GetMapping(value = "/commandes/{id}")
    public Optional<Commande> recupererUneCommande(@PathVariable int id){

        Optional<Commande> commande = commandesDao.findById(id);

        if(!commande.isPresent()) throw new CommandeNotFoundException("Cette commande n'existe pas");

        return commande;
    }
}
