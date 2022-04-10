package MkonerLivraison.GestionUtilisateurs.service.impl;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static MkonerLivraison.GestionUtilisateurs.constant.UtilisateurImplConstant.*;
import MkonerLivraison.GestionUtilisateurs.entity.Client;
import MkonerLivraison.GestionUtilisateurs.enumeration.Role;
import MkonerLivraison.GestionUtilisateurs.exception.domain.EmailExistException;
import MkonerLivraison.GestionUtilisateurs.exception.domain.EmailNotFoundException;
import MkonerLivraison.GestionUtilisateurs.exception.domain.UserNotFoundException;
import MkonerLivraison.GestionUtilisateurs.exception.domain.UsernameExistException;
import MkonerLivraison.GestionUtilisateurs.exception.domain.UsernameNotFoundException;
import MkonerLivraison.GestionUtilisateurs.params.CreateClientParams;
import MkonerLivraison.GestionUtilisateurs.params.CreateUtilisateurParam;
import MkonerLivraison.GestionUtilisateurs.params.UpdateClientParams;
import MkonerLivraison.GestionUtilisateurs.repository.ClientRepository;
import MkonerLivraison.GestionUtilisateurs.service.ClientService;
import MkonerLivraison.GestionUtilisateurs.service.LoginAttemptService;

@Service
@Qualifier("userDetailsService")
public class ClientServiceImpl implements ClientService, UserDetailsService{
	
	@Autowired
	private LoginAttemptService loginAttemptService;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	private Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Override
	public Client createClient(CreateUtilisateurParam createUtilisateurParam, CreateClientParams createClientParams)
			throws EmailExistException, UsernameExistException, UserNotFoundException, UsernameNotFoundException, EmailNotFoundException {
		String auths [] = {};
		Client client = new Client(createUtilisateurParam,createClientParams);
		validateNewUsernameAndEmail(null, client.getUsename(), client.getEmail());
		client.setRole(Role.CLIENT.name());
		client.setUserAuthorities(auths);
		clientRepository.save(client);
		return client;
	}
	
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Client clientByUsername = clientRepository.findByUsername(username);
        Client clientByEmail = clientRepository.findByEmail(username);
          if(clientByEmail == null) {
            validateLoginAttempt(clientByUsername);
            clientByUsername.setLastLoginDateDisplay(clientByUsername.getLastLoginDate());
            clientByUsername.setLastLoginDate(new Date());
            clientRepository.save(clientByUsername);
            LOGGER.info(FOUND_USER_BY_USERNAME + username);
            return clientByUsername;
        } else if(clientByUsername == null) {
            validateLoginAttempt(clientByEmail);
            clientByEmail.setLastLoginDateDisplay(clientByEmail.getLastLoginDate());
            clientByEmail.setLastLoginDate(new Date());
            clientRepository.save(clientByUsername);
            LOGGER.info(FOUND_USER_BY_USERNAME + username);
            return clientByEmail;
        } else {
            LOGGER.error(NO_USER_FOUND_BY_USERNAME + username);
            throw new UsernameNotFoundException(NO_USER_FOUND_BY_USERNAME + username);
        }
              
    }

	@Override
	public Client updateClient(String currentUsername, UpdateClientParams updateClientParams)
			throws UsernameNotFoundException, EmailExistException, UsernameExistException, UserNotFoundException,
			EmailNotFoundException {
		Client currentClient = validateNewUsernameAndEmail(currentUsername, updateClientParams.getUsename(), updateClientParams.getEmail());
		if (StringUtils.isNotBlank(updateClientParams.getName())) {
				currentClient.setName(updateClientParams.getName());
				}
		if (StringUtils.isNotBlank(updateClientParams.getUsename())) {
			currentClient.setUsename(updateClientParams.getUsename());
			}
		if (StringUtils.isNotBlank(updateClientParams.getEmail())) {
			currentClient.setName(updateClientParams.getEmail());
			}
		if (StringUtils.isNotBlank(updateClientParams.getPassword())) {
			currentClient.setPassword(encodePassword(updateClientParams.getEmail()));
			}
		if (updateClientParams.getTypeCompte()=="PRO") {
			if (StringUtils.isNotBlank(updateClientParams.getNomDeSociete())) {
				currentClient.setNomDeSociete(updateClientParams.getNomDeSociete());
				}
			if (StringUtils.isNotBlank(updateClientParams.getContactSociete())) {
				currentClient.setContactSociete(updateClientParams.getContactSociete());
				}
			if (StringUtils.isNotBlank(updateClientParams.getContactSociete2())) {
				currentClient.setContactSociete2(updateClientParams.getContactSociete2());
				}
			if (StringUtils.isNotBlank(updateClientParams.getEmailSociete())) {
				currentClient.setEmailSociete(updateClientParams.getEmailSociete());
				}
			if (StringUtils.isNotBlank(updateClientParams.getAddressSocieteLingne1())) {
				currentClient.setAddressSocieteLingne1(updateClientParams.getAddressSocieteLingne1());
				}
			if (StringUtils.isNotBlank(updateClientParams.getAddressSocieteLingne2())) {
				currentClient.setAddressSocieteLingne2(updateClientParams.getAddressSocieteLingne2());
				}
			
			}
		return null;
	}
	
	public Client lockOrUnlock(String username) throws UserNotFoundException{
		Client client = clientRepository.findByUsername(username);
		client.setNotLocked(!client.isNotLocked());
		clientRepository.save(client);
		return client;	
	}

	@Override
	public Client findClientByEmail(String email) throws EmailNotFoundException {
		// TODO Auto-generated method stub
		return clientRepository.findByEmail(email);
	}

	@Override
	public Client findClientByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return clientRepository.findByUsername(username);
	}

	@Override
	public Client findClientById(Long id) {
		// TODO Auto-generated method stub
		return clientRepository.getById(id);
	}

	@Override
	public void deleteClient(Client client) {
		// TODO Auto-generated method stub

	}
	
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
	
	private void validateLoginAttempt(Client user) {
        if(user.isNotLocked()) {
            if(loginAttemptService.hasExceededMaxAttempts(user.getUsername())) {
                user.setNotLocked(false);
            } else {
                user.setNotLocked(true);
            }
        } else {
            loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
        }
    }

    private Client validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws
    UserNotFoundException, UsernameExistException, EmailExistException, UsernameNotFoundException, EmailNotFoundException {
    	Client userByNewUsername = findClientByUsername(newUsername);
    	Client userByNewEmail = findClientByEmail(newEmail);
        if(StringUtils.isNotBlank(currentUsername)) {
        	Client currentUser = findClientByUsername(currentUsername);
            if(currentUser == null) {
                throw new UserNotFoundException(NO_USER_FOUND_BY_USERNAME + currentUsername);
            }
            if(userByNewUsername != null && !currentUser.getId().equals(userByNewUsername.getId())) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS);
            }
            if(userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
                 throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return currentUser;
        } else {
            if(userByNewUsername != null) {
                throw new UsernameExistException(USERNAME_ALREADY_EXISTS); 
            }
            if(userByNewEmail != null) {
                throw new EmailExistException(EMAIL_ALREADY_EXISTS);
            }
            return null;
        }
    }


}
