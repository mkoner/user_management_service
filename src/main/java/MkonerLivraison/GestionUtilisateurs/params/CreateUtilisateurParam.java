package MkonerLivraison.GestionUtilisateurs.params;

import java.util.Date;

import lombok.Data;

@Data
public abstract class CreateUtilisateurParam {
	
	protected String name;
	protected String usename;
	protected String email;
	protected String password;
	protected Date joinDate = new Date();
	protected String role;
	protected String[] userAuthorities;
	protected boolean isActive;
	protected boolean isNotLocked;
}
