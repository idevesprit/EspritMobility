package tn.esprit.mobiliteinternationall.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.mobiliteinternationall.entites.Candidat;
import tn.esprit.mobiliteinternationall.entites.Candidature;
import tn.esprit.mobiliteinternationall.entites.Favori;
import tn.esprit.mobiliteinternationall.entites.Offre;
import tn.esprit.mobiliteinternationall.repositories.CandidatRepository;
import tn.esprit.mobiliteinternationall.repositories.CandidatureRepository;
import tn.esprit.mobiliteinternationall.repositories.FavoriRepository;
import tn.esprit.mobiliteinternationall.repositories.OffreRepository;

import java.util.List;
@AllArgsConstructor
@Service
public class IFavoriServiceImp  implements IFavoriService {

    FavoriRepository favoriRepository;

    CandidatRepository candidatRepository;
    OffreRepository offreRepository;



    @Override
    public Favori updateFavori(Favori favori) {
        return favoriRepository.save(favori);
    }

    @Override
    public Favori getById(Integer idFavori) {
        return favoriRepository.findById(idFavori).orElse(null);
    }

    @Override
    public List<Favori> getAllFavoris() {
        return favoriRepository.findAll();
    }

    @Override
    public void removeFavori(Integer idFavori) {
        favoriRepository.deleteById(idFavori);
    }

    @Override
    public void assignCandidatToOffre(Favori favori, Integer idCandidat, Integer idOffre) {
        Candidat candidat = candidatRepository.findById(idCandidat).orElse(null);
        Offre offre = offreRepository.findById(idOffre).orElse(null);
        favori.setCandidat(candidat);
        favori.setOffre(offre);
        favoriRepository.save(favori);

    }


}
