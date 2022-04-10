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
public class Utilisateur implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String name;
	protected String usename;
	protected String email;
	protected String password;
	protected Date lastLoginDate;
	protected Date lastLoginDateDisplay;
	protected Date joinDate;
	protected String role;
	protected String[] userAuthorities;
	protected boolean isActive;
	protected boolean isNotLocked;
		
	public Utilisateur (CreateUtilisateurParam createUtilisateurParam) {
		this.name = createUtilisateurParam.getName();
		this.usename = createUtilisateurParam.getUsename();
		this.email = createUtilisateurParam.getEmail();
		this.password = createUtilisateurParam.getPassword();
		this.role = createUtilisateurParam.getRole();
		this.userAuthorities = createUtilisateurParam.getUserAuthorities();
		this.isActive = createUtilisateurParam.isActive();
		this.isNotLocked = createUtilisateurParam.isNotLocked();
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
		return this.getUsename();
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
