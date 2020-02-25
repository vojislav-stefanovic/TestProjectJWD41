package jwd.wafepa.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwd.wafepa.model.Skakaonica;
import jwd.wafepa.repository.SkakaonicaRepository;
import jwd.wafepa.service.SkakaonicaService;

@Service
public class JpaSkakaonicaService implements SkakaonicaService {

	@Autowired
	private SkakaonicaRepository skakonicaRepository;

	@Override
	public List<Skakaonica> findAll() {
		return skakonicaRepository.findAll();
	}

	@Override
	public Skakaonica findOne(Long id) {
		return skakonicaRepository.findOne(id);
	}

	@Override
	public Skakaonica save(Skakaonica s) {
		return skakonicaRepository.save(s);
	}

}
