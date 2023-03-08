package tn.esprit.mobiliteinternationall.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.mobiliteinternationall.entites.Candidat;
import tn.esprit.mobiliteinternationall.entites.ServiceUniversite;
import tn.esprit.mobiliteinternationall.entites.ServiceUniversiteNonDisponibleException;
import tn.esprit.mobiliteinternationall.repositories.CandidatRepository;
import tn.esprit.mobiliteinternationall.repositories.ServiceUniversiteRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class ServiceCandidat implements ICandidatService {

	@Autowired
	CandidatRepository candidatRepository;
	@Autowired
	ServiceUniversiteRepository serviceUniversiteRepository;

	@Override
	public Candidat addCandidat(Candidat E) {
		return candidatRepository.save(E);
	}

	@Override
	public Candidat updateCandidat(Candidat E) {
		return candidatRepository.save(E);
	}

	@Override
	public Candidat getCandidatId(Integer id) {
		return candidatRepository.findById(id).orElse(null);
	}

	@Override
	public List<Candidat> getAllCandidats() {

		return candidatRepository.findAll();
	}

	@Override
	public void removeCandidat(Integer id) {

		candidatRepository.deleteById(id);
	}

	public void assignServiceUniversiteToCandidat(Integer idService, Integer idCandidat) {

		Candidat candidat = candidatRepository.findById(idCandidat).get();
		ServiceUniversite serviceUniversite = serviceUniversiteRepository.findById(idService).get();
		//serviceUniversite1.reduirePrixService();
		candidat.setServiceUniversite(serviceUniversite);

		candidatRepository.save(candidat);

		ServiceUniversite service = serviceUniversiteRepository.findById(idService).orElse(null);
		if (service == null) {
			// Gérer l'erreur si l'objet n'est pas trouvé
			throw new EntityNotFoundException("Service Universite non trouvé pour l'id : " + idService);
		}

		// Vérifier si le service est disponible
		if (serviceUniversite.getDisponibilite() > 0) {
			// Décrémenter la disponibilité de 1 et affecter le candidat au service
			serviceUniversite.setDisponibilite(service.getDisponibilite() - 1);
			candidat.setServiceUniversite(service);
			candidatRepository.save(candidat);
			serviceUniversiteRepository.save(service);
		} else {
			// Gérer l'erreur si le service n'est pas disponible
			throw new ServiceUniversiteNonDisponibleException("ce service universite est complet pour l'id : " + idService);
		}


	}
}