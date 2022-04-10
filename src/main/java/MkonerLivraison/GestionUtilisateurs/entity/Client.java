package MkonerLivraison.GestionUtilisateurs.entity;

import javax.persistence.*;


import MkonerLivraison.GestionUtilisateurs.params.CreateClientParams;
import MkonerLivraison.GestionUtilisateurs.params.CreateUtilisateurParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client extends Utilisateur{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false) 
	private Long id;
    private String typeCompte;
    private String nomDeSociete;
    private String contactSociete;
    private String contactSociete2;
    private String emailSociete;
    private String addressSocieteLingne1;
    private String addressSocieteLingne2;
    
    private AgentClient[] agentClient;
    
    public Client (CreateUtilisateurParam createUtilisateurParam,CreateClientParams createClientParams) {   
		super(createUtilisateurParam);
		this.typeCompte = createClientParams.getTypeCompte();
		this.nomDeSociete = createClientParams.getNomDeSociete();
		this.contactSociete = createClientParams.getContactSociete();
		this.contactSociete2 = createClientParams.getContactSociete2();
		this.emailSociete = createClientParams.getEmailSociete();
		this.addressSocieteLingne1 = createClientParams.getAddressSocieteLingne1();
		this.addressSocieteLingne2 = createClientParams.getAddressSocieteLingne2();
    }
   
}
