package tn.esprit.mobiliteinternationall.services;

import tn.esprit.mobiliteinternationall.entites.Commentaire;

import java.util.List;

public interface ICommentaireServices {
    public Commentaire addCommentaire(Commentaire c,Integer idCandidat,Integer idOffre);
    public Commentaire updateCommentaire(Commentaire c,Integer idCandidat);
    public Commentaire getCommentaireById(Integer idCommentaire);
    public List<Commentaire> getAllCommentaire();
    public void removeCommentaire(Integer idCommentaire,Integer idCandidat);

    void assignCommentaireToCandidat (Integer idCommentaire, Integer idCandidat);

    //tri par date
    List<Commentaire> triComment();

   // public Commentaire likeComment(int idCommentaire);
   // public Commentaire dislikeComment(int idCommentaire);

}
