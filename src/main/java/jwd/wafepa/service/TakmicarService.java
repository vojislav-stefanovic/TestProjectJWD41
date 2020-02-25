package jwd.wafepa.service;

import java.util.List;

import org.springframework.data.domain.Page;

import jwd.wafepa.model.Takmicar;

public interface TakmicarService {

	Takmicar findOne(Long id);

	Takmicar save(Takmicar t);

	Takmicar delete(Long id);

	Page<Takmicar> findAll(int page);

	Page<Takmicar> pretraga(String imeIprezime, String drzava, Integer minGodiste, Integer maxGodiste, int page);


}
