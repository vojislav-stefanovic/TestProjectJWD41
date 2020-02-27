package jwd.wafepa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tbl_skakaonica")
public class Skakaonica {

    @Id
    @GeneratedValue
    @Column
    private Long id;
    @Column(nullable = false, unique = true)
    private String naziv;
    @Column
    private Double k;
    @Column
    @DecimalMin("1.2")
    @DecimalMax("4.8")
    private Double d;

    @OneToMany(mappedBy = "skakaonica")
    private List<Takmicar> takmicari = new ArrayList<>();

    public Skakaonica() {

    }

    public Skakaonica(String naziv, Double k, Double d) {
        this.naziv = naziv;
        this.k = k;
        this.d = d;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Double getK() {
        return k;
    }

    public void setK(Double k) {
        this.k = k;
    }

    public Double getD() {
        return d;
    }

    public void setD(Double d) {
        this.d = d;
    }

    public List<Takmicar> getTakmicari() {
        return takmicari;
    }

    public void setTakmicari(List<Takmicar> takmicari) {
        this.takmicari = takmicari;
    }

    public void addTakmicar(Takmicar t) {
        if (this.takmicari.size() >= 15) {
            throw new IllegalStateException("Ne moze biti vise od 15 takmicara po skakaonici");
        } else {
            this.takmicari.add(t);
            if (!this.equals(t.getSkakaonica())) {
                t.setSkakaonica(this);
            }
        }

    }

}