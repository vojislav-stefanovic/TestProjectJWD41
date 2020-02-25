package jwd.wafepa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_skok")
public class Skok {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private Double daljina;
	@Column
	private Double poeniZaDaljinu;
	@Column
	private Double ocenasudija;
	@Column
	private Double zbirPoena;

	@ManyToOne(fetch = FetchType.EAGER)
	private Takmicar takmicar;

	public Skok(Double daljina, Double poeniZaDaljinu, Double ocenasudija, Double zbirPoena) {
		this.daljina = daljina;
		this.poeniZaDaljinu = poeniZaDaljinu;
		this.ocenasudija = ocenasudija;
		this.zbirPoena = zbirPoena;
	}

	public void setTakmicar(Takmicar takmicar) {
		this.takmicar = takmicar;
		if (!takmicar.getSkokovi().contains(this)) {
			takmicar.getSkokovi().add(this);
		}
	}

}
