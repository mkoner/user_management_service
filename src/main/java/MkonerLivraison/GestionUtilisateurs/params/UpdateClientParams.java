package MkonerLivraison.GestionUtilisateurs.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClientParams extends UpdateUtilisateurParams{
	private String firstName;
	private String laststName;
	private String usename;
	private String email;
	private String password;
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
}
