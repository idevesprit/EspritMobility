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
    
	public Reclamation retrieveReclamation(int id) {
		return recrepo.findById(id).orElse(null);
	}
	
    public List<Reclamation> getallReclamations() {
        return (List<Reclamation>) recrepo.findAll();
    }
    
	public Reclamation updateReclamation(int idReclamation, Reclamation reclamation) {
		recrepo.findById(idReclamation);
		return recrepo.save(reclamation);
	}

    public void deleteReclamation(int id) {
        recrepo.deleteById(id);
    }

}
