package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Skok;
import jwd.wafepa.service.SkokService;
import jwd.wafepa.support.SkokToSkokDTO;
import jwd.wafepa.web.dto.SkokDTO;

@RestController
@RequestMapping(value = "/api/skokovi")
public class ApiSkokController {

	@Autowired
	private SkokService skokService;

	@Autowired
	private SkokToSkokDTO toDTO;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<SkokDTO>> getAll() {

		List<Skok> skokovi = skokService.findAll();

		if (skokovi == null || skokovi.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(toDTO.convert(skokovi), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{id}")
	public ResponseEntity<SkokDTO> skok(
			@PathVariable Long id,
			@RequestParam(value="sudija1") Double sudija1,
			@RequestParam(value="sudija2") Double sudija2,
			@RequestParam(value="sudija3") Double sudija3, 
			@RequestParam(value="sudija4") Double sudija4,
			@RequestParam(value="sudija5") Double sudija5, 
			@RequestParam(value="daljina") Double daljina) {

		Skok skok = skokService.skok(id, sudija1, sudija2, sudija3, sudija4, sudija5, daljina);

		if (skok == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<>(toDTO.convert(skok), HttpStatus.OK);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
