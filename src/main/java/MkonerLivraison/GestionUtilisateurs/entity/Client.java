package MkonerLivraison.GestionUtilisateurs.entity;

import static java.util.Arrays.stream;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import javax.persistence.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import MkonerLivraison.GestionUtilisateurs.params.CreateClientParams;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client extends Utilisateur implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false) 
	private Long id;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String password;
	private Date lastLoginDate;
	private Date lastLoginDateDisplay;
	private Date joinDate;
	private String role;
	private String[] userAuthorities;
	private boolean isActive;
	private boolean isNotLocked;
    private String typeCompte;
    private String nomDeSociete;
    private String contactSociete;
    private String contactSociete2;
    private String emailSociete;
    private String addressSocieteLingne1;
    private String addressSocieteLingne2;
    
    private AgentClient[] agentClient;
    
    public Client (CreateClientParams createClientParams) {  
    	this.firstName = createClientParams.getFirstName();
    	this.lastName = createClientParams.getLastName();
    	this.username = createClientParams.getUsername();
    	this.email = createClientParams.getEmail();
    	this.password = createClientParams.getPassword();
    	this.userAuthorities = createClientParams.getUserAuthorities();
    	this.isActive = createClientParams.isActive();
    	this.isNotLocked = createClientParams.isNotLocked();
    	this.joinDate = createClientParams.getJoinDate();
    	this.typeCompte = createClientParams.getTypeCompte();
		this.nomDeSociete = createClientParams.getNomDeSociete();
		this.contactSociete = createClientParams.getContactSociete();
		this.contactSociete2 = createClientParams.getContactSociete2();
		this.emailSociete = createClientParams.getEmailSociete();
		this.addressSocieteLingne1 = createClientParams.getAddressSocieteLingne1();
		this.addressSocieteLingne2 = createClientParams.getAddressSocieteLingne2();
    }
   
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return stream(this.getUserAuthorities()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.isNotLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.isActive();
	}
    
}
