package MkonerLivraison.GestionUtilisateurs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import MkonerLivraison.GestionUtilisateurs.entity.Operateur;

public interface OperateurRepository extends JpaRepository<Operateur, Long> {
	Operateur findByUsername(String unsername);
	 Operateur findByEmail(String email);

}
