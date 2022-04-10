package MkonerLivraison.GestionUtilisateurs.params;

import lombok.Data;

@Data
public abstract class UpdateUtilisateurParams {
	
	protected String name;
	protected String usename;
	protected String email;
	protected String password;
	protected String role;
	protected String[] userAuthorities; 
	protected boolean isActive;
	protected boolean isNotLocked;

}
