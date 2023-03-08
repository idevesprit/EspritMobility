
package tn.esprit.mobiliteinternationall.services;

import org.hibernate.collection.internal.PersistentBag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.mobiliteinternationall.entites.Candidat;
import tn.esprit.mobiliteinternationall.entites.ServiceUniversite;
import tn.esprit.mobiliteinternationall.entites.Universite;
import tn.esprit.mobiliteinternationall.repositories.CandidatRepository;
import tn.esprit.mobiliteinternationall.repositories.ServiceUniversiteRepository;
import tn.esprit.mobiliteinternationall.repositories.UniversiteRepository;

import java.util.List;

@Service
public class ServiceUniversiteServices implements IServiceUniversiteServices {
    @Autowired
    ServiceUniversiteRepository serviceUniversiteRepository;
    @Autowired
    CandidatRepository candidatRepository;
    @Autowired
    UniversiteRepository universiteRepository;
    public Candidat[] candidats;
    public float priceService ;
    private PersistentBag candidat;


    @Override
    public ServiceUniversite addServiceUniversitee(ServiceUniversite s) {
        return serviceUniversiteRepository.save(s);
    }

    @Override
    public void removeServiceUniversite(int idService) {
        serviceUniversiteRepository.deleteById(idService);
    }

    @Override
    public ServiceUniversite updateServiceUniversite(ServiceUniversite s) {
        return serviceUniversiteRepository.save(s);
    }

    @Override
    public ServiceUniversite getById(int idService) {
        return serviceUniversiteRepository.findById(idService).orElse(null);
    }

    @Override
    public List<ServiceUniversite> getAllServiceUniversite() {

        return serviceUniversiteRepository.findAll();
    }

    @Override
    public void assignServiceUniversiteToCandidat(Integer idService, Integer idCandidat) {

    }


    @Override
    public void assignServiceUniversiteToUniversite(Integer idService, Integer idUniversite) {
        ServiceUniversite serviceUniversite = serviceUniversiteRepository.findById(idService).get();
        Universite universite = universiteRepository.findById(idUniversite).get();
        serviceUniversite.setUniversite(universite);
        serviceUniversiteRepository.save(serviceUniversite);


    }
    @Override

    public float calculateTotalPrice() {
        List<ServiceUniversite> services = serviceUniversiteRepository.findAll();
        float totalPrice = 0.0F;
        for (ServiceUniversite serviceUniversite : services) {
            totalPrice += serviceUniversite.getPriceService();
        }
        return (float) totalPrice;
    }

    @Override
    public float getTotalPriceForService(ServiceUniversite serviceUniversite) {
        float totalPrice = 0.0F;
        List<Candidat> candidats = serviceUniversite.getCandidats();
        for (Candidat candidat1 : serviceUniversite.getCandidats()) {
            totalPrice += serviceUniversite.getPriceService();
            // Si chaque candidat n'a pas de prix, il faudrait peut-être ajuster le code ici
        }
        return totalPrice;
    }

}




