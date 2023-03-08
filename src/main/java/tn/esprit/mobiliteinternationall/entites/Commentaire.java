package tn.esprit.mobiliteinternationall.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Commentaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private int idCommentaire;
    private String libelle;
    @Temporal(TemporalType.DATE)
    private Date dateCommentaire;
   // private int likeCommentaire;
   // private int dislikeCommentaire;


    @ManyToOne
    Candidat candidat;
    //////////////////////add new
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    Offre offre;

    @OneToMany(mappedBy = "Likecommentaire")
    private List<LikeComment> likeCS;

    @OneToMany(mappedBy = "dislikecommentaire")
    private  List<DislikeComment> dislikeCS;
}
