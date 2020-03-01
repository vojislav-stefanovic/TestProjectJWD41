package jwd.wafepa.support;

import jwd.wafepa.model.Skok;
import jwd.wafepa.model.Takmicar;
import jwd.wafepa.service.TakmicarService;
import jwd.wafepa.web.dto.SkokDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component

public class SkokDTOtoSkok implements Converter<SkokDTO, Skok> {

	@Autowired
	private TakmicarService takmicarService;

	@Override
	public Skok convert(SkokDTO dto) {
		if (dto == null) {
			return null;
		}
		Skok s = new Skok();
		s.setDaljina(dto.getDaljina());
		s.setId(dto.getId());
		s.setOcenasudija(dto.getOcenasudija());
		s.setZbirPoena(dto.getZbirPoena());
		s.setPoeniZaDaljinu(dto.getPoeniZaDaljinu());
		Takmicar t = takmicarService.findOne(dto.getTakmicarId());
		s.setTakmicar(t);
		return s;
	}

}
