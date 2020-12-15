package tn.rnu.isi.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tn.rnu.isi.model.Client;
import tn.rnu.isi.service.ClientService;
import tn.rnu.isi.service.CommandeService;



 
@Controller("clientController")
public class ClientController {
	
	private final Logger logger = LoggerFactory.getLogger(ClientController.class);

	
	@Autowired //The @Autowired annotation tells Spring where an injection needs to occur. 
	ClientService clientService;
	
	
	@Autowired
	CommandeService commandeService;
 
 

@RequestMapping(value = "/client/listAll", method = RequestMethod.GET)  // equivalent to @GetMapping 

	protected ModelAndView showAllclients() throws Exception {
		/*
		 * Lancement du Service et recupeation donnees en base
		 */
		List<Client> listeclients = clientService.getAll();

		/*
		 * Envoi Vue + Modele MVC pour Affichage donnees vue
		 */
		return new ModelAndView("/client/showAllClients", "clients", listeclients); // 
	}

	 	@RequestMapping(value = "/client/list", method = RequestMethod.GET)
	    public String list(Model model) throws Exception {
	        model.addAttribute("clients", clientService.getAll());
	        return "/client/showAllClients"; // Afficher la page showAllclients.html qui se trouve sous /client
	        
	    }

	    @RequestMapping(value = "/client/get/{id}" , method = RequestMethod.GET)
	    public String get(@PathVariable Long id, Model model) throws Exception {
	        model.addAttribute("clientToShow", clientService.getByIdClient(id));
	        return "/client/showClient"; // Afficher la page showclient.html qui se trouve sous /client
	    }
	    
	    
	    @RequestMapping(value = "/client/save", method = RequestMethod.POST)
	    public String saveOrUpdate(@ModelAttribute("clientForm") Client client, Model model, final RedirectAttributes redirectAttributes) throws Exception {
	    	try {
				
			
	    		if(client.getIdClient()!=null){
	    			
	    			 clientService.save(client);
	    			
					redirectAttributes.addFlashAttribute("typeAlert", "update");
			    	redirectAttributes.addFlashAttribute("msgAlert", "client dont ID : "+client.getIdClient()+" a été mis à jour.");
			    	
			     
			     
				}else{
					
					Long idclient = clientService.save(client);
					
					redirectAttributes.addFlashAttribute("typeAlert", "new");
			    	redirectAttributes.addFlashAttribute("msgAlert", "Nouveau client a été enregsitrée avec ID : "+idclient);
				}
	    		
			

 	    	
	    	
	    	
	    	} catch (Exception e) {
				e.printStackTrace();
			}
	        return "redirect:/client/listAll";
	    }
	    
	    

 
	    @RequestMapping("/client/update/{id}")
	    public String update(@PathVariable Long id, Model model, final RedirectAttributes redirectAttributes) throws Exception {
	        Client client = clientService.getByIdClient(id);
	        model.addAttribute("clientForm", client);
	        return "/client/addUpdateClient";
	    }
	    
	    @RequestMapping(value = "/client/delete/{id}")
	    public String delete(@PathVariable Long id, final RedirectAttributes redirectAttributes) throws Exception {
	    	commandeService.deleteCommandeByIdClient(id);
	        clientService.deleteClient(id);
	        
	        redirectAttributes.addFlashAttribute("typeAlert", "delete");
	    	redirectAttributes.addFlashAttribute("msgAlert", "client dont ID : "+id+" a été supprimé.");
	    	
	        return "redirect:/client/listAll";
	    }
	    
	    @RequestMapping(value = "/client/clear") //path hetha y3aytlou le boutton supprimer tout fil html
	    public String deleteAll() throws Exception {
	    	List<Client> listeclients = clientService.getAll();
	    	for (Client client : listeclients) {
		    	commandeService.deleteCommandeByIdClient(client.getIdClient());	
		    	clientService.deleteClient(client.getIdClient());
			}
	    	
	        return "redirect:/client/listAll";
	    }
 
}
