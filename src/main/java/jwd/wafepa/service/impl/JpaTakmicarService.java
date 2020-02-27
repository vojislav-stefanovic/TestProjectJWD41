package jwd.wafepa.service.impl;

import jwd.wafepa.model.Takmicar;
import jwd.wafepa.repository.TakmicarRepository;
import jwd.wafepa.service.TakmicarService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class JpaTakmicarService implements TakmicarService {

    private @NonNull TakmicarRepository takmicarRepository;

    @Override
    public Takmicar findOne(Long id) {
        return takmicarRepository.findOne(id);
    }

    @Override
    public Takmicar save(Takmicar t) {
        return takmicarRepository.save(t);
    }

    @Override
    public Takmicar delete(Long id) {
        Takmicar t = takmicarRepository.findOne(id);
        takmicarRepository.delete(id);
        return t;


    }

    @Override
    public Page<Takmicar> findAll(int page) {
        return takmicarRepository.findAll(new PageRequest(page, 6));
    }

    @Override
    public Page<Takmicar> pretraga(String imeIprezime, String drzava, Integer minGodiste, Integer maxGodiste,
                                   int page) {
        if (imeIprezime != null) {
            imeIprezime = "%" + imeIprezime + "%";
        }
        if (drzava != null) {
            drzava = "%" + drzava + "%";
        }
        return takmicarRepository.pretraga(imeIprezime, drzava, minGodiste, maxGodiste, new PageRequest(page, 6));
    }

}
