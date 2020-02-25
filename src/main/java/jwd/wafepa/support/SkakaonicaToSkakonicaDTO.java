package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Skakaonica;
import jwd.wafepa.web.dto.SkakaonicaDTO;

@Component
public class SkakaonicaToSkakonicaDTO implements Converter<Skakaonica, SkakaonicaDTO> {

	@Override
	public SkakaonicaDTO convert(Skakaonica s) {
		if (s == null) {
			return null;
		}
		SkakaonicaDTO dto = new SkakaonicaDTO();
		dto.setD(s.getD());
		dto.setId(s.getId());
		dto.setK(s.getK());
		dto.setNaziv(s.getNaziv());
		return dto;
	}

	public List<SkakaonicaDTO> convert(List<Skakaonica> skakaonice) {
		List<SkakaonicaDTO> retVal = new ArrayList<>();
		for (Skakaonica s : skakaonice) {
			SkakaonicaDTO dto = convert(s);
			retVal.add(dto);
		}
		return retVal;
	}

}
