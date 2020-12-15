package tn.rnu.isi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.rnu.isi.model.Client;
import tn.rnu.isi.model.Produit;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {
	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Long save(Client client) throws Exception {
		client= clientRepository.save(client);
				return client.getIdClient();
	}

	@Override
	public List<Client> getAll() {
		return (List<Client>) clientRepository.findAll() ;
	}

	@Override
	public Client getByIdClient(Long idClient) throws Exception {
		 return  (Client) clientRepository.findByIdClient(idClient);
	}

	@Override
	public int updateId(Long idClient) {
		return clientRepository.updateIdClient(idClient);
	}

	@Override
	public void deleteClient(Long idClient) {
		clientRepository.delete(idClient) ;
		
	}

}
