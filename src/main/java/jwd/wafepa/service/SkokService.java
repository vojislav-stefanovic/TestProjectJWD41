package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Skok;

public interface SkokService {
	
	Skok skok(Long id, Double sudija1, Double sudija2, Double sudija3, Double sudija4, Double sudija5, Double daljina);
	
	List<Skok> findAll();
	
	Skok save(Skok skok);

	List<Skok> findByTakmicarId(Long takmicarId);
}
