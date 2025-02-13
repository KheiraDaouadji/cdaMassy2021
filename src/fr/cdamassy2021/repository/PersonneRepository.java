package fr.cdamassy2021.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.cdamassy2021.entity.Personne;

@Repository
public interface PersonneRepository extends CrudRepository<Personne, Long> {
	
	@Query(value = "SELECT p FROM Personne p WHERE p.email = ?1 "
			+ " AND p.pwd = ?2 ")
	public Personne exist(String email, String pwd);
	
	@Query(value = "SELECT p.id_personne,p.nom,p.prenom, p.email, p.pwd, p.tel, "
			+ "p.est_formateur, p.est_gestionnaire, p.est_administrateur\n"
            + "FROM membre_canal mc\n"
            + "		INNER JOIN personne p\n"
            + "			ON mc.id_personne = p.id_personne\n"
            + "WHERE id_canal=?" 
            , nativeQuery = true)
	public Collection<Personne> findMembreByCanal(int idCanal);
	
}

