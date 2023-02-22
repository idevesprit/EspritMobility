package tn.esprit.mobiliteinternationall.services;

import tn.esprit.mobiliteinternationall.entites.Candidature;
import tn.esprit.mobiliteinternationall.entites.Favori;
import tn.esprit.mobiliteinternationall.entites.RoleCandidat;

import java.util.List;

public interface IFavoriService {

    public Favori updateFavori(Favori favori);
    public Favori getById(Integer idFavori);
    public List<Favori> getAllFavoris();
    public void removeFavori(Integer idFavori);
    public void assignCandidatToOffre(Favori favori, Integer idCandidat, Integer idOffre);
}
