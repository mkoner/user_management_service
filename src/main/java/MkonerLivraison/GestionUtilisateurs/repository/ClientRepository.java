package MkonerLivraison.GestionUtilisateurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import MkonerLivraison.GestionUtilisateurs.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	Client findByUsername(String unsername);
	Client findByEmail (String email);
}