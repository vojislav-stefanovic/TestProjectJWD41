package jwd.wafepa;

import jwd.wafepa.model.Skakaonica;
import jwd.wafepa.model.Takmicar;
import jwd.wafepa.service.SkakaonicaService;
import jwd.wafepa.service.TakmicarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TestData {

	@Autowired
	private TakmicarService takmicarService;
	@Autowired
	private SkakaonicaService skakaonicaService;

	@PostConstruct
	public void init() {

		Skakaonica s1 = new Skakaonica("Skakaonica1", 85.0, 1.2);
		Skakaonica s2 = new Skakaonica("Skakaonica2", 90.0, 1.4);

		skakaonicaService.save(s1);
		skakaonicaService.save(s2);

		Takmicar t1 = new Takmicar("Hari Oli", "Finska", 192.0, "oli@mail.com", 1987);
		Takmicar t2 = new Takmicar("Martin Smit", "Nemacka", 193.0, "martin@mail.com", 1989);
		Takmicar t3 = new Takmicar("Sep Brandl", "Austrija", 187.0, "sep@mail.com", 1987);
		Takmicar t4 = new Takmicar("Jakub Banda", "Slovacka", 177.0, "jakub@mail.com", 1993);
		Takmicar t5 = new Takmicar("Jane Ahonen", "Finska", 183.0, "jane@mail.com", 1992);

		takmicarService.save(t1);
		takmicarService.save(t2);
		takmicarService.save(t3);
		takmicarService.save(t4);
		takmicarService.save(t5);
		
		s1.addTakmicar(t1);
		s1.addTakmicar(t3);
		s1.addTakmicar(t4);
		s2.addTakmicar(t2);
		s2.addTakmicar(t5);

		skakaonicaService.save(s1);
		skakaonicaService.save(s2);

		takmicarService.save(t1);
		takmicarService.save(t2);
		takmicarService.save(t3);
		takmicarService.save(t4);
		takmicarService.save(t5);

	}
}
