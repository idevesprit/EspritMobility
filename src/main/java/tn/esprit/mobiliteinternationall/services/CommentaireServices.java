package tn.esprit.mobiliteinternationall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.mobiliteinternationall.entites.Candidat;
import tn.esprit.mobiliteinternationall.entites.Commentaire;
import tn.esprit.mobiliteinternationall.repositories.CandidatRepository;
import tn.esprit.mobiliteinternationall.repositories.CommentaireRepository;

import java.util.List;

@Service
public class CommentaireServices implements ICommentaireServices {

    @Autowired
    CommentaireRepository commentaireRepository;

    @Autowired
    CandidatRepository candidatRepository;

    @Override
    public Commentaire addCommentaire(Commentaire c) {
        return commentaireRepository.save(c);
    }

    @Override
    public Commentaire updateCommentaire(Commentaire c) {
        return commentaireRepository.save(c);
    }

    @Override
    public Commentaire getCommentaireById(Integer idCommentaire) {
        return commentaireRepository.findById(idCommentaire).orElse(null);
    }

    @Override
    public List<Commentaire> getAllCommentaire() {
        return commentaireRepository.findAll();
    }

    @Override
    public void removeCommentaire(Integer idCommentaire) {
        commentaireRepository.deleteById(idCommentaire);
    }

    @Override
    public void assignCommentaireToCandidat(Integer idCommentaire, Integer idCandidat) {
        Commentaire commentaire = commentaireRepository.findById(idCommentaire).get();
        Candidat candidat = candidatRepository.findById(idCandidat).get();
        commentaire.setCandidat(candidat);
        commentaireRepository.save(commentaire);
    }

    @Override
    public Commentaire likeComment(int idCommentaire ) {
        Commentaire comment = commentaireRepository.findById(idCommentaire).orElse(null);
        comment.setLikeCommentaire(comment.getLikeCommentaire()+1);
        return commentaireRepository.save(comment);
    }

    @Override
    public Commentaire dislikeComment(int idCommentaire) {
        Commentaire comment = commentaireRepository.findById(idCommentaire).orElse(null);
        comment.setDislikeCommentaire(comment.getDislikeCommentaire()+1);
        return commentaireRepository.save(comment);
    }

}
