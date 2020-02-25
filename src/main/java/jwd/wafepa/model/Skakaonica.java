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
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    public Skakaonica(String naziv, Double k, Double d) {
        this.naziv = naziv;
        this.k = k;
        this.d = d;
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