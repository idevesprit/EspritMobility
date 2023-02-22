package tn.esprit.mobiliteinternationall.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.mobiliteinternationall.entites.Candidat;
import tn.esprit.mobiliteinternationall.repositories.CandidatRepository;

@Service
public class CandidatServices implements ICandidatServices{
    @Autowired
    CandidatRepository candidatRepository;
    @Override
    public Candidat getById(int idCandidat) {
        return  candidatRepository.findById(idCandidat).orElse(null);
    }
}
