package tn.esprit.mobiliteinternationall.services;

import tn.esprit.mobiliteinternationall.entites.Commentaire;

import java.util.List;

public interface ICommentaireServices {
    public Commentaire addCommentaire(Commentaire c);
    public Commentaire updateCommentaire(Commentaire c);
    public Commentaire getCommentaireById(Integer idCommentaire);
    public List<Commentaire> getAllCommentaire();
    public void removeCommentaire(Integer idCommentaire);

    void assignCommentaireToCandidat (Integer idCommentaire, Integer idCandidat);

    public Commentaire likeComment(int idCommentaire);
    public Commentaire dislikeComment(int idCommentaire);

}
