package jwd.wafepa.web.controller;

import java.util.List;
import java.util.Optional;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import jwd.wafepa.model.Skok;
import jwd.wafepa.model.Takmicar;
import jwd.wafepa.service.SkokService;
import jwd.wafepa.service.TakmicarService;
import jwd.wafepa.support.SkokToSkokDTO;
import jwd.wafepa.support.TakmicarDTOtoTakmicar;
import jwd.wafepa.support.TakmicarToTakmicarDTO;
import jwd.wafepa.web.dto.SkokDTO;
import jwd.wafepa.web.dto.TakmicarDTO;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping(value = "/api/takmicari")
public class ApiTakmicariController {

    private @NonNull TakmicarService takmicarService;
    private @NonNull TakmicarDTOtoTakmicar toTakmicar;
    private @NonNull TakmicarToTakmicarDTO toDTO;
    private @NonNull SkokToSkokDTO toSkokDTO;
    private @NonNull SkokService skokService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TakmicarDTO>> getAll(
            @RequestParam(required = false) String imeIprezime,
            @RequestParam(required = false) String drzava,
            @RequestParam(required = false) Integer minGodiste,
            @RequestParam(required = false) Integer maxGodiste,
            @RequestParam(defaultValue = "0") int page) {

        Page<Takmicar> takmicariPaged = null;
        List<Takmicar> takmicari = null;

        takmicariPaged = getTakmicars(imeIprezime, drzava, minGodiste, maxGodiste, page);

        takmicari = takmicariPaged.getContent();

        if (takmicari == null || takmicari.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("totalPages", Integer.toString(takmicariPaged.getTotalPages()));

        return new ResponseEntity<>(toDTO.convert(takmicari), headers, HttpStatus.OK);
    }

    private Page<Takmicar> getTakmicars(String imeIprezime, String drzava, Integer minGodiste,
                                        Integer maxGodiste, int page) {

        Page<Takmicar> takmicariPaged;
        if (imeIprezime != null || drzava != null || minGodiste != null || maxGodiste != null) {
            takmicariPaged = takmicarService.pretraga(imeIprezime, drzava, minGodiste, maxGodiste, page);
        } else {
            takmicariPaged = takmicarService.findAll(page);
        }
        return takmicariPaged;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<TakmicarDTO> getOne(@PathVariable Long id) {

        Takmicar takmicar = takmicarService.findOne(id);

        if (takmicar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(toDTO.convert(takmicar), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<TakmicarDTO> delete(@PathVariable Long id) {

        Takmicar takmicar = takmicarService.findOne(id);
        if (takmicar == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        takmicarService.delete(id);

        return new ResponseEntity<>(toDTO.convert(takmicar), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<TakmicarDTO> save(@RequestBody @Validated TakmicarDTO takmicarDTO) {

        if (takmicarDTO == null || takmicarDTO.getSkakaonicaId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Takmicar saved = takmicarService.save(toTakmicar.convert(takmicarDTO));

        return new ResponseEntity<>(toDTO.convert(saved), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public ResponseEntity<TakmicarDTO> edit(@RequestBody @Validated TakmicarDTO takmicarDTO, @PathVariable Long id) {

        if (!id.equals(takmicarDTO.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (takmicarDTO == null || takmicarDTO.getSkakaonicaId() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Takmicar edited = takmicarService.save(toTakmicar.convert(takmicarDTO));

        return new ResponseEntity<>(toDTO.convert(edited), HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/{takmicarId}/skokovi")
    public ResponseEntity<List<SkokDTO>> getSkokovi(@PathVariable Long takmicarId) {

        List<Skok> skokovi = skokService.findByTakmicarId(takmicarId);

        if (skokovi == null || skokovi.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }

        return new ResponseEntity<>(toSkokDTO.convert(skokovi), HttpStatus.OK);

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Void> handle() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
