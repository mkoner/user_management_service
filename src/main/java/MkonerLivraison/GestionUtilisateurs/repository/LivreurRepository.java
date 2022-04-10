 package MkonerLivraison.GestionUtilisateurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import MkonerLivraison.GestionUtilisateurs.entity.Livreur;


public interface LivreurRepository extends JpaRepository<Livreur, Long> {
	Livreur findByUsername(String unsername);
	Livreur findByEmail(String email);
}
