package tn.esprit.mobiliteinternationall.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Universite implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int idUniversite;
    private String nomUniversite;
    private String adresseUniversite;
    private String mail;
    private String password;
    private String description;

    private String pays;
    private String specialite;
    @Enumerated(EnumType.STRING)
    private TypeUniversite typeUniversite;

  //  @OneToMany(mappedBy = "universite")
  //  List<Service> services;
@JsonIgnore
    @OneToMany(mappedBy = "universite")
    List<Offre> offreList;
    @JsonIgnore
    @OneToMany(mappedBy = "universite")
    List<Entretien> entretienList;


}
