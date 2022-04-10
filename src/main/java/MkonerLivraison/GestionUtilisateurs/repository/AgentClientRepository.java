 package MkonerLivraison.GestionUtilisateurs.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import MkonerLivraison.GestionUtilisateurs.entity.AgentClient;

public interface AgentClientRepository extends JpaRepository<AgentClient, Long> {
	AgentClient findByUsername(String unsername);
	AgentClient findByEmail(String email);
}
