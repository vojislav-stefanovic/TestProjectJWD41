package jwd.wafepa.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Takmicar;
import jwd.wafepa.web.dto.TakmicarDTO;

@Component
public class TakmicarToTakmicarDTO implements Converter<Takmicar, TakmicarDTO> {

	@Override
	public TakmicarDTO convert(Takmicar t) {
		if (t == null) {
			return null;
		}
		TakmicarDTO dto = new TakmicarDTO();
		dto.setDrzava(t.getDrzava());
		dto.setEmail(t.getEmail());
		dto.setGodinaRodjenja(t.getGodinaRodjenja());
		dto.setId(t.getId());
		dto.setImeIprezime(t.getImeIprezime());
		dto.setNazivSkakaonice(t.getSkakaonica().getNaziv());
		dto.setSkakaonicaId(t.getSkakaonica().getId());
		dto.setVisina(t.getVisina());
		return dto;
	}

	public List<TakmicarDTO> convert(List<Takmicar> takmicari) {
		List<TakmicarDTO> retVal = new ArrayList<>();
		for (Takmicar t : takmicari) {
			TakmicarDTO dto = convert(t);
			retVal.add(dto);
		}
		return retVal;
	}

}
