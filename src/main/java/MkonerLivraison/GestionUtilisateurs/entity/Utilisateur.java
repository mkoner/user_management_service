package MkonerLivraison.GestionUtilisateurs.entity;


import static java.util.Arrays.stream;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import MkonerLivraison.GestionUtilisateurs.params.CreateUtilisateurParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class Utilisateur{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String firstName;
	protected String lastName;
	protected String username;
	protected String email;
	protected String password;
	protected Date lastLoginDate;
	protected Date lastLoginDateDisplay;
	protected Date joinDate;
	protected String role;
	protected String[] userAuthorities;
	protected boolean isActive;
	protected boolean isNotLocked;
			
	

	
	
}
