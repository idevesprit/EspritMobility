package tn.esprit.mobiliteinternationall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.mobiliteinternationall.entites.Reclamation;
import tn.esprit.mobiliteinternationall.repositories.ReclamationRepository;

import java.util.List;

@Service
public class ReclamationService {
    @Autowired
    ReclamationRepository recrepo;



    public Reclamation addReclamation(Reclamation r) {

        return recrepo.save(r);
    }

    public List<Reclamation> getallReclamations() {

        return (List<Reclamation>) recrepo.findAll();
    }



    public void deleteReclamation(int id) {
        recrepo.deleteById(id);

    }

    public Reclamation updateReclamation(Reclamation r) {

        return recrepo.save(r);
    }
}
