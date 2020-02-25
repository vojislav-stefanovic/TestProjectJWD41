package jwd.wafepa.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import jwd.wafepa.model.Takmicar;

@Repository
public interface TakmicarRepository extends JpaRepository<Takmicar, Long> {
	
	Page<Takmicar> findAll(Pageable pageable);
	
	@Query(
			"select t from Takmicar t where "
			+ "(:imeIprezime is null or t.imeIprezime like :imeIprezime) and "
			+ "(:drzava is null or t.drzava like :drzava) and "
			+ "(:minGodiste is null or t.godinaRodjenja >= :minGodiste) and "
			+ "(:maxGodiste is null or t.godinaRodjenja <= :maxGodiste)"
			)
	Page<Takmicar> pretraga(
			@Param("imeIprezime") String imeIprezime, 
			@Param("drzava") String drzava, 
			@Param("minGodiste") Integer minGodiste, 
			@Param("maxGodiste") Integer maxGodiste, 
			Pageable pageable);

}
