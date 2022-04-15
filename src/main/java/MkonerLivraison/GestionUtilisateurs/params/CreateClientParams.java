package MkonerLivraison.GestionUtilisateurs.params;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateClientParams extends CreateUtilisateurParam {
	
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String password;
	private Date joinDate = new Date();
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
