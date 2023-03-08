package tn.esprit.mobiliteinternationall.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.mobiliteinternationall.entites.Candidat;
import tn.esprit.mobiliteinternationall.entites.Commentaire;
import tn.esprit.mobiliteinternationall.entites.DislikeComment;
import tn.esprit.mobiliteinternationall.entites.LikeComment;
import tn.esprit.mobiliteinternationall.repositories.CandidatRepository;
import tn.esprit.mobiliteinternationall.repositories.CommentaireRepository;
import tn.esprit.mobiliteinternationall.repositories.DislikeRepository;
import tn.esprit.mobiliteinternationall.repositories.LikeRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReactionServices implements IReactionServices {

    @Autowired
    CommentaireRepository commentaireRepository;
    @Autowired
    LikeRepository likeRepository;

    @Autowired
    DislikeRepository dislikeRepository;
    @Autowired
    CandidatRepository candidatRepository;

    @Transactional
    @Override
    public LikeComment addAndAssignLikeToCommentAndCandidat(LikeComment l, Integer idCandidat, Integer idCommentaire) {
        Candidat candidat = candidatRepository.findById(idCandidat).orElse(null);
        Commentaire commentaire = commentaireRepository.findById(idCommentaire).orElse(null);

        if (candidat != null && commentaire != null) {
            if (candidat.getDislikeCS() == null && candidat.getLikeCS()==null ){
                LikeComment like1 = likeRepository.save(l);
                like1.setLikecommentaire(commentaire);
                like1.setCandidat(candidat);
                //candidat.setLikeCS(l);
            } else if (candidat.getDislikeCS() != null) {
                candidat.setDislikeCS(null);
                LikeComment like = likeRepository.save(l);
                like.setLikecommentaire(commentaire);
                like.setCandidat(candidat);
                //candidat.setLikeCS(l);
            }
        }
        return null;
       // return l ;
    }

    //////////////////////////////////////
    @Transactional
    @Override
    public DislikeComment addAndAssignDislikeToCommentAndCandidat(DislikeComment l, Integer idCandidat, Integer idCommentaire) {
        Candidat candidat = candidatRepository.findById(idCandidat).orElse(null);
        Commentaire commentaire = commentaireRepository.findById(idCommentaire).orElse(null);

        if (candidat != null && commentaire != null) {
            if (candidat.getDislikeCS() == null && candidat.getLikeCS()==null ){
                DislikeComment dislike1 = dislikeRepository.save(l);
                dislike1.setDislikecommentaire(commentaire);
                dislike1.setCandidat(candidat);
                //candidat.setLikeCS(l);
            } else if (candidat.getLikeCS() != null) {
                candidat.setLikeCS(null);
                DislikeComment dislike = dislikeRepository.save(l);
                dislike.setDislikecommentaire(commentaire);
                dislike.setCandidat(candidat);
                //candidat.setLikeCS(l);
            }
        }
        return null;
        // return l ;
    }

}
