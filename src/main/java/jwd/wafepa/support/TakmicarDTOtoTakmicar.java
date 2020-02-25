package jwd.wafepa.support;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import jwd.wafepa.model.Skakaonica;
import jwd.wafepa.model.Takmicar;
import jwd.wafepa.service.SkakaonicaService;
import jwd.wafepa.service.TakmicarService;
import jwd.wafepa.web.dto.TakmicarDTO;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TakmicarDTOtoTakmicar implements Converter<TakmicarDTO, Takmicar> {

	private @NonNull TakmicarService takmicarService;
	private @NonNull SkakaonicaService skakaonicaService;

	@Override
	public Takmicar convert(TakmicarDTO dto) {
		Takmicar t;
		if (dto.getId() == null) {
			t = new Takmicar();
		} else {
			t = takmicarService.findOne(dto.getId());
			if (t == null) {
				throw new IllegalArgumentException("Ne postoji takmicar sa tim id-om");
			}
		}
		t.setDrzava(dto.getDrzava());
		t.setEmail(dto.getEmail());
		t.setGodinaRodjenja(dto.getGodinaRodjenja());
		t.setId(dto.getId());
		t.setImeIprezime(dto.getImeIprezime());
		t.setVisina(dto.getVisina());
		Skakaonica s = skakaonicaService.findOne(dto.getSkakaonicaId());
		t.setSkakaonica(s);
		return t;
	}

}
