package MkonerLivraison.GestionUtilisateurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import MkonerLivraison.GestionUtilisateurs.entity.Proprietaire;

public interface ProprietaireRepository extends JpaRepository<Proprietaire, Long> {
	Proprietaire findByUsername(String unsername);
	Proprietaire findByEmail(String email);
}
