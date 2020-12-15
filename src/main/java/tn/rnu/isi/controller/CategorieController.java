package tn.rnu.isi.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tn.rnu.isi.model.Categorie;
import tn.rnu.isi.service.CategorieService;
import tn.rnu.isi.service.CommandeService;

@Controller("categorieController")
public class CategorieController {
private final Logger logger = LoggerFactory.getLogger(CategorieController.class);

	
	@Autowired //The @Autowired annotation tells Spring where an injection needs to occur. 
	CategorieService categorieService;
	
	
	@Autowired
	CommandeService commandeService;
 
 

@RequestMapping(value = "/categorie/listAll", method = RequestMethod.GET)  // equivalent to @GetMapping 

	protected ModelAndView showAllcategories() throws Exception {
		/*
		 * Lancement du Service et recupeation donnees en base
		 */
		List<Categorie> listecategories = categorieService.getAll();

		/*
		 * Envoi Vue + Modele MVC pour Affichage donnees vue
		 */
		return new ModelAndView("/categorie/showAllCategories", "categories", listecategories); // 
	}

	 	@RequestMapping(value = "/categorie/list", method = RequestMethod.GET)
	    public String list(Model model) throws Exception {
	        model.addAttribute("categories", categorieService.getAll());
	        return "/categorie/showAllCategories"; // Afficher la page showAllcategories.html qui se trouve sous /categorie
	        
	    }

	    @RequestMapping(value = "/categorie/get/{id}" , method = RequestMethod.GET)
	    public String get(@PathVariable Long id, Model model) throws Exception {
	        model.addAttribute("categorieToShow", categorieService.getByIdCategorie(id));
	        return "/categorie/showCategorie"; // Afficher la page showcategorie.html qui se trouve sous /categorie
	    }
	    
	    
	    @RequestMapping(value = "/categorie/save", method = RequestMethod.POST)
	    public String saveOrUpdate(@ModelAttribute("categorieForm") Categorie categorie, Model model, final RedirectAttributes redirectAttributes) throws Exception {
	    	try {
				
			
	    		if(categorie.getIdCateg()!=null){
	    			
	    			 categorieService.save(categorie);
	    			
					redirectAttributes.addFlashAttribute("typeAlert", "update");
			    	redirectAttributes.addFlashAttribute("msgAlert", "categorie dont ID : "+categorie.getIdCateg()+" a été mis à jour.");
			    	
			     
			     
				}else{
					
					Long idcategorie = categorieService.save(categorie);
					
					redirectAttributes.addFlashAttribute("typeAlert", "new");
			    	redirectAttributes.addFlashAttribute("msgAlert", "Nouveau categorie a été enregsitrée avec ID : "+idcategorie);
				}
	    		
			

 	    	
	    	
	    	
	    	} catch (Exception e) {
				e.printStackTrace();
			}
	        return "redirect:/categorie/listAll";
	    }
	    
	    

 
	    @RequestMapping("/categorie/update/{id}")
	    public String update(@PathVariable Long id, Model model, final RedirectAttributes redirectAttributes) throws Exception {
	        Categorie categorie = categorieService.getByIdCategorie(id);
	        model.addAttribute("categorieForm", categorie);
	        return "/categorie/addUpdateCategorie";
	    }
	    
	    @RequestMapping(value = "/categorie/delete/{id}")
	    public String delete(@PathVariable Long id, final RedirectAttributes redirectAttributes) throws Exception {
	    	try {
	        categorieService.deleteCategorie(id);
	        redirectAttributes.addFlashAttribute("typeAlert", "delete");
	    	redirectAttributes.addFlashAttribute("msgAlert", "categorie dont ID : "+id+" a été supprimé.");
	    	}
	    	
	    	catch(Exception e)
	    	{
	    		redirectAttributes.addFlashAttribute("msgAlert", "Un ou plusieurs produits sont affiliés à cette categorie !");
	    	}
	        
	    
	        return "redirect:/categorie/listAll";
	    }
	    
	    @RequestMapping(value = "/categorie/clear") //path hetha y3aytlou le boutton supprimer tout fil html
	    public String deleteAll() throws Exception {
	    	List<Categorie> listecategories = categorieService.getAll();
	    	for (Categorie categorie : listecategories) {
		    	categorieService.deleteCategorie(categorie.getIdCateg());
			}
	    	
	        return "redirect:/categorie/listAll";
	    }
}
