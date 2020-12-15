package tn.rnu.isi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tn.rnu.isi.model.Categorie;

public interface CategorieRepository extends CrudRepository <Categorie, Long>  {

	Categorie findByIdCateg(Long idCateg);
	 
	List<Categorie> findAll();
	
	Categorie save (Categorie categorie); 
	 
	@Modifying
	@Query("update Categorie u set u.idCateg = ?1")
	int updateIdCategorie(Long idCateg);
	

 	@Transactional
 	@Modifying
	@Query("delete from Categorie u where u.idCateg = ?1")
	void delete(Long idCateg);
}
