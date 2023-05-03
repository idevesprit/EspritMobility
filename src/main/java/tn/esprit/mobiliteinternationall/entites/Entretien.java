package tn.esprit.mobiliteinternationall.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entretien implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int idEntretien;
    @Temporal(TemporalType.DATE)
    private Date dateEntretien;
    @Enumerated(EnumType.STRING)
    private StatutEntretien statutEntretien;

    @JsonIgnore
    @ManyToOne
    Universite universite;
    @JsonIgnore

    @ManyToOne
    Candidature candidature;
}
