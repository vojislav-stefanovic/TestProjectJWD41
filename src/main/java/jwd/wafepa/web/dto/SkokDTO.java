package jwd.wafepa.web.dto;

public class SkokDTO {

	private Long id;
	private Double daljina;
	private Double poeniZaDaljinu;
	private Double ocenasudija;
	private Double zbirPoena;
	private Long takmicarId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getDaljina() {
		return daljina;
	}

	public void setDaljina(Double daljina) {
		this.daljina = daljina;
	}

	public Double getPoeniZaDaljinu() {
		return poeniZaDaljinu;
	}

	public void setPoeniZaDaljinu(Double poeniZaDaljinu) {
		this.poeniZaDaljinu = poeniZaDaljinu;
	}

	public Double getOcenasudija() {
		return ocenasudija;
	}

	public void setOcenasudija(Double ocenasudija) {
		this.ocenasudija = ocenasudija;
	}

	public Double getZbirPoena() {
		return zbirPoena;
	}

	public void setZbirPoena(Double zbirPoena) {
		this.zbirPoena = zbirPoena;
	}

	public Long getTakmicarId() {
		return takmicarId;
	}

	public void setTakmicarId(Long takmicarId) {
		this.takmicarId = takmicarId;
	}

}
