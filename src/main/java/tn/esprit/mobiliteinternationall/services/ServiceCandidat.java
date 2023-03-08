package tn.esprit.mobiliteinternationall.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.mobiliteinternationall.entites.Candidat;
import tn.esprit.mobiliteinternationall.repositories.CandidatRepository;







@Service
public class ServiceCandidat  {

	@Autowired
	CandidatRepository candidatRepository  ;
	
	
	public Candidat addCandidat(Candidat E) {
		return CandidatRepository.save(E);
	}

	
	public Candidat updateCandidat(Candidat E) {
		return candidatRepository.save(E);	}

	
	public Candidat getCandidatId(Integer id) {
		return candidatRepository.findById(id).orElse(null);
	}

	
	public List<Candidat> getAllCandidats() {
		
		return candidatRepository.findAll();
	}

	
	public void removeCandidat(Integer id) {
		
		candidatRepository.deleteById(id);
	}

	
	
}
