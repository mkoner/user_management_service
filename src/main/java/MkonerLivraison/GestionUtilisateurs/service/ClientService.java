package MkonerLivraison.GestionUtilisateurs.service;

import org.springframework.security.core.userdetails.UserDetails;

import MkonerLivraison.GestionUtilisateurs.entity.Client;
import MkonerLivraison.GestionUtilisateurs.exception.domain.EmailExistException;
import MkonerLivraison.GestionUtilisateurs.exception.domain.EmailNotFoundException;
import MkonerLivraison.GestionUtilisateurs.exception.domain.UserNotFoundException;
import MkonerLivraison.GestionUtilisateurs.exception.domain.UsernameExistException;
import MkonerLivraison.GestionUtilisateurs.exception.domain.UsernameNotFoundException;
import MkonerLivraison.GestionUtilisateurs.params.CreateClientParams;
import MkonerLivraison.GestionUtilisateurs.params.CreateUtilisateurParam;
import MkonerLivraison.GestionUtilisateurs.params.UpdateClientParams;

public interface ClientService {
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
	Client createClient(CreateUtilisateurParam createUtilisateurParam,CreateClientParams createClientParams) throws 
	EmailExistException, UsernameExistException, UserNotFoundException, UsernameNotFoundException, EmailNotFoundException;
	Client updateClient(String currentUsername, UpdateClientParams updateClientParams) throws UsernameNotFoundException, 
	      EmailExistException, UsernameExistException, UserNotFoundException, EmailNotFoundException;
	Client lockOrUnlock(String username) throws UserNotFoundException;
	Client findClientByEmail(String email) throws EmailNotFoundException;
	Client findClientByUsername(String username) throws UsernameNotFoundException;
	Client findClientById(Long id);
	void deleteClient(Client client);
}
 