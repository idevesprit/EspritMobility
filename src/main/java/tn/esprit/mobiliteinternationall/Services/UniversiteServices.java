package tn.esprit.mobiliteinternationall.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.mobiliteinternationall.entites.Candidat;
import tn.esprit.mobiliteinternationall.entites.ServiceUniversite;
import tn.esprit.mobiliteinternationall.entites.Universite;
import tn.esprit.mobiliteinternationall.repositories.UniversiteRepository;

@Service
public class UniversiteServices implements IUniversiteServices{
    @Autowired
    UniversiteRepository universiteRepository;

    @Override
    public Universite getById(int idUniversite) {
        return  universiteRepository.findById(idUniversite).orElse(null);
    }
}
