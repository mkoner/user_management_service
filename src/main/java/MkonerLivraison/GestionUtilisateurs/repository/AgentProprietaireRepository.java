package MkonerLivraison.GestionUtilisateurs.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import MkonerLivraison.GestionUtilisateurs.entity.AgentProprietaire;

public interface AgentProprietaireRepository extends JpaRepository<AgentProprietaire, Long> {
	AgentProprietaire findByUsername(String unsername);
	AgentProprietaire findByEmail(String email);
}
