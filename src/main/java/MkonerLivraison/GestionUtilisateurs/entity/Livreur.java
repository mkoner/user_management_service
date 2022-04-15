package MkonerLivraison.GestionUtilisateurs.entity;

import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Livreur extends Utilisateur {

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
	private String pieceIdentite;
	private boolean disponibilite; 
	
}
