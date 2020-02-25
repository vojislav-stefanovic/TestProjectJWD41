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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

	public Takmicar(String imeIprezime, String drzava, Double visina, String email, Integer godinaRodjenja) {
		this.imeIprezime = imeIprezime;
		this.drzava = drzava;
		this.visina = visina;
		this.email = email;
		this.godinaRodjenja = godinaRodjenja;
	}

	public void addSkok(Skok s) {
		this.skokovi.add(s);
		if (!this.equals(s.getTakmicar())) {
			s.setTakmicar(this);
		}
	}

}
