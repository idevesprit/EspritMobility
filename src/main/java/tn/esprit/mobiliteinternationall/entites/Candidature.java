package tn.esprit.mobiliteinternationall.entites;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Candidature implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int idCandidature;
    @Temporal(TemporalType.DATE)
    private Date debutMobilite;

    @Column(name = "CV")
    @Lob
    private byte[] cv;
    @Column(name = "LettreDeMotivation")
    @Lob
    private byte[] lettreMotivation;

    private StatutCandidature statutCandidature;

    private Float moyenne;

   /* @Transient
    private String releveNote;*/


   /* @Transient
    private String attestationTravail;
    @Transient
    private int nbrAnneeTravail;
*/
    @ManyToOne
    Candidat candidat;

    @ManyToOne
    Offre offre;

    @OneToMany(mappedBy = "candidature")
    List<Entretien> entretientList;

  /*  public Candidature(byte[] cv) {
        this.cv = cv;
    }*/
}
