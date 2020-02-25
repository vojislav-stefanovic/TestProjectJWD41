package jwd.wafepa.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwd.wafepa.model.Skakaonica;
import jwd.wafepa.service.SkakaonicaService;
import jwd.wafepa.support.SkakaonicaToSkakonicaDTO;
import jwd.wafepa.web.dto.SkakaonicaDTO;

@RestController
@RequestMapping(value = "/api/skakaonice")
public class ApiSkakaonicaController {

    @Autowired
    private SkakaonicaService skakaoniceService;

    @Autowired
    private SkakaonicaToSkakonicaDTO toDTO;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<SkakaonicaDTO>> getAll() {

        List<Skakaonica> skakaonice = skakaoniceService.findAll();

        if (skakaonice == null || skakaonice.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDTO.convert(skakaonice), HttpStatus.OK);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Void> handle() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
