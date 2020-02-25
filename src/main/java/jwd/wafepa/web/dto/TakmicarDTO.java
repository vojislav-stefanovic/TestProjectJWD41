package jwd.wafepa.web.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

public class TakmicarDTO {

	private Long id;
	private String imeIprezime;
	@Size(max = 35, min = 2)
	private String drzava;
	private Double visina;
	@Email
	private String email;
	private Integer godinaRodjenja;
	private Long skakaonicaId;
	private String nazivSkakaonice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImeIprezime() {
		return imeIprezime;
	}

	public void setImeIprezime(String imeIprezime) {
		this.imeIprezime = imeIprezime;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public Double getVisina() {
		return visina;
	}

	public void setVisina(Double visina) {
		this.visina = visina;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getGodinaRodjenja() {
		return godinaRodjenja;
	}

	public void setGodinaRodjenja(Integer godinaRodjenja) {
		this.godinaRodjenja = godinaRodjenja;
	}

	public Long getSkakaonicaId() {
		return skakaonicaId;
	}

	public void setSkakaonicaId(Long skakaonicaId) {
		this.skakaonicaId = skakaonicaId;
	}

	public String getNazivSkakaonice() {
		return nazivSkakaonice;
	}

	public void setNazivSkakaonice(String nazivSkakaonice) {
		this.nazivSkakaonice = nazivSkakaonice;
	}

}
