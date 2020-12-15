package tn.rnu.isi.service;

import java.util.List;

import tn.rnu.isi.model.Client;

public interface ClientService {
public Long save (Client Client) throws Exception ; //persistance
	
	List<Client> getAll();
 
	Client getByIdClient(Long idClient) throws Exception;
	
	int updateId (Long idClient);
	
  	
  	void deleteClient(Long idClient);
}
