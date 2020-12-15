package tn.rnu.isi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.rnu.isi.model.Categorie;
import tn.rnu.isi.model.Categorie;

@Service
@Transactional
public class CategorieServiceImpl  implements CategorieService{
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	
	@Override
	public Long save(Categorie categorie) throws Exception {
		categorie = categorieRepository.save(categorie);
		return categorie.getIdCateg();
	}

	@Override
	public List<Categorie> getAll() {
		return (List<Categorie>) categorieRepository.findAll() ;
	}

	@Override
	public Categorie getByIdCategorie(Long idCategorie) throws Exception {
		 return  (Categorie) categorieRepository.findByIdCateg(idCategorie);
	}

	@Override
	public int updateId(Long idCategorie) {
		return categorieRepository.updateIdCategorie(idCategorie);
	}

	@Override
	public void deleteCategorie(Long idCategorie) {
		categorieRepository.delete(idCategorie) ;
		
	}

}
