package tn.rnu.isi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tn.rnu.isi.model.Client;


public interface ClientRepository extends CrudRepository <Client, Long> {
	
	//repository = SQL reperesentation of ClientService interface 
		Client findByIdClient(Long idClient);
		
		 
		List<Client> findAll(); //CrudRepo function that returns all clients
		
		Client save (Client Client); // CrudRepo function that saves (INSERT) or Updates an object
		 
		@Modifying
		@Query("update Client u set u.idClient = ?1")
		int updateIdClient(Long idClient);
		

	 	@Transactional
	 	@Modifying
		@Query("delete from Client u where u.idClient = ?1")
		void delete(Long idClient);

}
