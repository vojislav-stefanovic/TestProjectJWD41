package jwd.wafepa.service;

import java.util.List;

import jwd.wafepa.model.Skakaonica;

public interface SkakaonicaService {

	List<Skakaonica> findAll();

	Skakaonica findOne(Long id);

	Skakaonica save(Skakaonica s);

}
