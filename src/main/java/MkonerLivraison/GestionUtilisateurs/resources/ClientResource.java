package MkonerLivraison.GestionUtilisateurs.resources;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import static org.springframework.http.HttpStatus.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import MkonerLivraison.GestionUtilisateurs.constant.SecurityConstante;
import MkonerLivraison.GestionUtilisateurs.entity.Client;
import MkonerLivraison.GestionUtilisateurs.exception.ExceptionHandling;
import MkonerLivraison.GestionUtilisateurs.exception.domain.EmailExistException;
import MkonerLivraison.GestionUtilisateurs.exception.domain.EmailNotFoundException;
import MkonerLivraison.GestionUtilisateurs.exception.domain.UserNotFoundException;
import MkonerLivraison.GestionUtilisateurs.exception.domain.UsernameExistException;
import MkonerLivraison.GestionUtilisateurs.exception.domain.UsernameNotFoundException;
import MkonerLivraison.GestionUtilisateurs.params.CreateClientParams;
import MkonerLivraison.GestionUtilisateurs.service.ClientService;
import MkonerLivraison.GestionUtilisateurs.utility.JWTTokenProvider;

@RestController
@RequestMapping("/clients")
public class ClientResource extends ExceptionHandling{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JWTTokenProvider jwtTokenProvider;
	@Autowired
	private ClientService clientService;
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@PostMapping("/login")
	public ResponseEntity<Client> login(@RequestBody Client client) throws UsernameNotFoundException {
		authenticate(client.getUsername(), client.getPassword());
		UserDetails loggedInClientUserDetails = clientService.loadUserByUsername(client.getUsername());
		HttpHeaders jwtHeader = getJwtHeader(loggedInClientUserDetails);
		Client loggedInClient = (Client) loggedInClientUserDetails;
		return new ResponseEntity<>(loggedInClient,jwtHeader,OK);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Client> register(@RequestBody CreateClientParams client) throws EmailExistException, 
	  UsernameExistException, UserNotFoundException, UsernameNotFoundException, EmailNotFoundException {
		LOGGER.info("Started register method"+client);
		Client newClient = clientService.createClient(client);
		LOGGER.info("create client invoked");
		return new ResponseEntity<>(newClient,OK);
	}

	private HttpHeaders getJwtHeader(UserDetails loggedInClient) {
		 HttpHeaders headers = new HttpHeaders();
		 headers.add(SecurityConstante.JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(loggedInClient));
	     return headers;
	}

	private void authenticate(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		
	}

}
