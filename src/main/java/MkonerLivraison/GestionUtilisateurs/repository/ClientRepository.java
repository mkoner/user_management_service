package MkonerLivraison.GestionUtilisateurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import MkonerLivraison.GestionUtilisateurs.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
	Client findByUsername(String unsername);
	Client findByEmail (String email);
}
