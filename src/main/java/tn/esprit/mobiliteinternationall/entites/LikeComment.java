package tn.esprit.mobiliteinternationall.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeComment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLikeComment;

    @ManyToOne
    @JsonIgnore
    private Commentaire Likecommentaire;

    @ManyToOne
    @JsonIgnore
    private Candidat candidat;

}
