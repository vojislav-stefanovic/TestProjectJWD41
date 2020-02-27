package jwd.wafepa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_takmicar")
public class Takmicar {

    @Id
    @GeneratedValue
    @Column
    private Long id;
    @Column(nullable = false)
    private String imeIprezime;
    @Column
    private String drzava;
    @Column
    private Double visina;
    @Column(nullable = false, unique = true)
    private String email;
    @Column
    private Integer godinaRodjenja;

    @ManyToOne(fetch = FetchType.EAGER)
    private Skakaonica skakaonica;

    @OneToMany(mappedBy = "takmicar")
    private List<Skok> skokovi = new ArrayList<>();

    public Takmicar() {

    }

    public Takmicar(String imeIprezime, String drzava, Double visina, String email, Integer godinaRodjenja) {
        this.imeIprezime = imeIprezime;
        this.drzava = drzava;
        this.visina = visina;
        this.email = email;
        this.godinaRodjenja = godinaRodjenja;
    }

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

	public Skakaonica getSkakaonica() {
		return skakaonica;
	}

	public void setSkakaonica(Skakaonica skakaonica) {
		this.skakaonica = skakaonica;
		if(!skakaonica.getTakmicari().contains(this)){
			skakaonica.getTakmicari().add(this);
		}

	}

	public List<Skok> getSkokovi() {
		return skokovi;
	}

	public void setSkokovi(List<Skok> skokovi) {
		this.skokovi = skokovi;
	}

	public void addSkok(Skok s) {
        this.skokovi.add(s);
        if (!this.equals(s.getTakmicar())) {
            s.setTakmicar(this);
        }
    }

}
