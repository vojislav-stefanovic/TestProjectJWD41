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

    public Skok() {

    }

    public Skok(Double daljina, Double poeniZaDaljinu, Double ocenasudija, Double zbirPoena) {
        this.daljina = daljina;
        this.poeniZaDaljinu = poeniZaDaljinu;
        this.ocenasudija = ocenasudija;
        this.zbirPoena = zbirPoena;
    }

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

    public Takmicar getTakmicar() {
        return takmicar;
    }

    public void setTakmicar(Takmicar takmicar) {
        this.takmicar = takmicar;
        if (!takmicar.getSkokovi().contains(this)) {
            takmicar.getSkokovi().add(this);
        }
    }

}
