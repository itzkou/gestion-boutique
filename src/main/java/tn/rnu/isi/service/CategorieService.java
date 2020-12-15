package tn.rnu.isi.service;

import java.util.List;

import tn.rnu.isi.model.Categorie;

public interface CategorieService {

public Long save (Categorie categorie) throws Exception ; //persistance
	
	List<Categorie> getAll();
 
	Categorie getByIdCategorie(Long idCategorie) throws Exception;
	
	int updateId (Long idCategorie);
	
 
  	void deleteCategorie(Long idCategorie);
  	
}
