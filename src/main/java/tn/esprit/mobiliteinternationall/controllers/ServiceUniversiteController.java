package tn.esprit.mobiliteinternationall.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import tn.esprit.mobiliteinternationall.services.IServiceUniversiteServices;
import tn.esprit.mobiliteinternationall.entites.ServiceUniversite;
import tn.esprit.mobiliteinternationall.entites.TypeService;
import tn.esprit.mobiliteinternationall.repositories.ServiceUniversiteRepository;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/ServiceUniversite")
public class ServiceUniversiteController {
    IServiceUniversiteServices iServiceUniversiteServices;

    ServiceUniversiteRepository serviceUniversiteRepository;

    @PostMapping("/add")
    public ServiceUniversite addServiceUniversite(@RequestBody ServiceUniversite s) {
        return iServiceUniversiteServices.addServiceUniversitee(s);
    }

    @DeleteMapping("/remove/{idService}")
    public void removeServiceUniversite(@PathVariable int idService) {
        iServiceUniversiteServices.removeServiceUniversite(idService);
    }

    @PutMapping("/updateServiceUniversite")
    public ServiceUniversite updateServiceUniversite(@RequestBody ServiceUniversite s) {
        return iServiceUniversiteServices.updateServiceUniversite(s);
    }

    @GetMapping("/FindServiceUniversiteById/{idService}")
    public ServiceUniversite FindServiceUniversiteById(@PathVariable int idService) {
        return iServiceUniversiteServices.getById(idService);
    }

    @GetMapping("/allServiceUniversite")
    public List<ServiceUniversite> FindAllServiceUniversite() {
        return iServiceUniversiteServices.getAllServiceUniversite();
    }

    @PutMapping("/assignServiceUniversiteToCandidat/{idService}/{idCandidat}")
    public void assignServiceUniversiteToCandidat(@PathVariable Integer idService, @PathVariable Integer idCandidat) {
        iServiceUniversiteServices.assignServiceUniversiteToCandidat(idService, idCandidat);
    }

    @PutMapping("/assignServiceUniversiteToUniversite/{idService}/{idUniversite}")
    public void assignServiceUniversiteToUniversite(@PathVariable Integer idService, @PathVariable Integer idUniversite) {
        iServiceUniversiteServices.assignServiceUniversiteToUniversite(idService, idUniversite);


    }

    @GetMapping("/getPrixTotals/{idCandidat}")
    public Float getPrixTotals(@PathVariable Integer idCandidat) {

        return serviceUniversiteRepository.sum(idCandidat);
    }

    @GetMapping("/getPrixTotalsParService/{typeService}")
    public Float getPrixTotalsParService(@PathVariable TypeService typeService) {

        return serviceUniversiteRepository.typeService(typeService);
    }


    // @GetMapping("/getPrixTotals1/{typeService}")
    // public Float getPrixTotals1(@PathVariable  Integer disponibilite) {

    // return serviceUniversiteRepository.sum1(disponibilite);
    //  }
}