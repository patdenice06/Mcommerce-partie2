package com.clientui.controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clientui.beans.CommandeBean;
import com.clientui.beans.PaiementBean;
import com.clientui.beans.ProductBean;
import com.clientui.proxies.MicroserviceCommandeProxy;
import com.clientui.proxies.MicroservicePaiementProxy;
import com.clientui.proxies.MicroserviceProduitsProxy;
import com.mcommandes.model.Commande;

@Controller
public class ClientController {
	
	@Autowired
	MicroserviceProduitsProxy mProduitsProxy;
	
    @Autowired
    private MicroserviceCommandeProxy CommandesProxy;

    @Autowired
    private MicroservicePaiementProxy paiementProxy;
    
    /*
    * Étape (1)
    * Opération qui récupère la liste des produits et on les affichent dans la page d'accueil.
    * Les produits sont récupérés grâce à ProduitsProxy
    * On fini par rentourner la page Accueil.html à laquelle on passe la liste d'objets "produits" récupérés.
    * */
    @RequestMapping("/")
    public String accueil(Model model){
    	List<ProductBean> produits = mProduitsProxy.listeDesProduits();
    	model.addAttribute("produits", produits);
        return "Accueil";
    }
    
    /*
 	* Étape (2)
    * Opération qui récupère les détails d'un produit
    * On passe l'objet "produit" récupéré et qui contient les détails en question à  FicheProduit.html
    * */
    @RequestMapping("details-produit/{id}")
    public String ficheProduit(Model model, @PathVariable int id){
    	ProductBean produit = mProduitsProxy.recupererUnProduit(id);
    	model.addAttribute("produit", produit);
    	model.addAttribute("commande", new CommandeBean() );
        return "FicheProduit";
    }

    
    /*
    * Étape (3) et (4)
    * Opération qui fait appel au microservice de commande pour placer une commande et récupérer les détails de la commande créée
    * L'utilisateur saisie la quantité du produit qu'il veut commander dans le formulaire
    * */
    @PostMapping(value = "/commander-produit/{idProduit}/{montant}")
    public String passerCommandeFinale( @PathVariable int idProduit, @PathVariable Double montant, Model model, 
    									@ModelAttribute("commande") CommandeBean commande,
    									@ModelAttribute("produit") ProductBean produit) {
    	
      commande.setProductId(idProduit);
      commande.setDateCommande(new Date());
      commande.setCommandePayee(false);
      
      CommandeBean commandeAjoutee = CommandesProxy.ajouterCommande(commande);

	  //on passe à la vue l'objet commande et le montant de celle-ci afin d'avoir les informations nécessaire pour le paiement
	  model.addAttribute("commande", commandeAjoutee);
	  model.addAttribute("montant", montant * commande.getQuantite());
      
      return "Paiement";
    }
    
    
    /*
    * Étape (5)
    * Opération qui fait appel au microservice de paiement pour traiter un paiement.
    * Si le paiement est accepté, le champ Commande.commandePayee est mis à "true"
    * */
    @RequestMapping(value = "/payer-commande/{idCommande}/{montantCommande}")
    public String payerCommande(@PathVariable int idCommande, @PathVariable Double montantCommande, Model model, 
    							@ModelAttribute("commande") Optional<CommandeBean> commande){

        PaiementBean paiementAExcecuter = new PaiementBean();

        //on reseigne les détails du produit
        paiementAExcecuter.setIdCommande(idCommande);
        paiementAExcecuter.setMontant(montantCommande);
        paiementAExcecuter.setNumeroCarte(numcarte()); // on génère un numéro au hasard pour simuler une CB

        // On appel le microservice et (étape 7) on récupère le résultat qui est sous forme ResponseEntity<PaiementBean> ce qui va nous permettre de vérifier le code retour.
        ResponseEntity<PaiementBean> paiement = paiementProxy.payerUneCommande(paiementAExcecuter);

        Boolean paiementAccepte = false;
        //si le code est autre que 201 CREATED, c'est que le paiement n'a pas pu aboutir.
        if(paiement.getStatusCode() == HttpStatus.CREATED) {
                paiementAccepte = true;
                // Récupérerer la commande et changer le statut "non payée" de cette commande "payée"
                Optional<CommandeBean> commandeRecuperee = CommandesProxy.recupererUneCommande(idCommande);
                if( commandeRecuperee.isPresent() ) {
                	CommandeBean commandeBean = new CommandeBean();
                	commandeBean = commandeRecuperee.get();
                	commandeBean.setCommandePayee(true);
                	// On met à jour la commande
                	CommandesProxy.ajouterCommande(commandeBean);
                }
        }

        model.addAttribute("paiementOk", paiementAccepte); // on envoi un Boolean paiementOk à la vue

        return "Confirmation";
    }    
    
        
    @RequestMapping (value = "/commandes")
    public ResponseEntity<CommandeBean> listeCommandes(){
		return null;    	
    }
    
    //Génére une serie de 16 chiffres au hasard pour simuler vaguement une CB
    private Long numcarte() {

        return ThreadLocalRandom.current().nextLong(1000000000000000L,9000000000000000L );
    }    
    
}

