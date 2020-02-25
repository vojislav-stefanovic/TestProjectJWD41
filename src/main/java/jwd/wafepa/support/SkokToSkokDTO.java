package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Skok;
import jwd.wafepa.web.dto.SkokDTO;

@Component
public class SkokToSkokDTO implements Converter<Skok, SkokDTO> {

	@Override
	public SkokDTO convert(Skok s) {
		if (s == null) {
			return null;
		}
		SkokDTO dto = new SkokDTO();
		dto.setDaljina(s.getDaljina());
		dto.setId(s.getId());
		dto.setOcenasudija(s.getOcenasudija());
		dto.setPoeniZaDaljinu(s.getPoeniZaDaljinu());
		dto.setZbirPoena(s.getZbirPoena());
		dto.setTakmicarId(s.getTakmicar().getId());
		return dto;
	}

	public List<SkokDTO> convert(List<Skok> skokovi) {
		List<SkokDTO> retVal = new ArrayList<>();
		for (Skok s : skokovi) {
			SkokDTO dto = convert(s);
			retVal.add(dto);
		}
		return retVal;
	}

}
