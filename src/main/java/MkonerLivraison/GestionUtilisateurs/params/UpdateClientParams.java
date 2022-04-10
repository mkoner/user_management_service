package MkonerLivraison.GestionUtilisateurs.params;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateClientParams extends UpdateUtilisateurParams{
	private String typeCompte;
    private String nomDeSociete;
    private String contactSociete;
    private String contactSociete2;
    private String emailSociete;
    private String addressSocieteLingne1;
    private String addressSocieteLingne2;
}
