package tn.esprit.mobiliteinternationall.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.mobiliteinternationall.entites.ResourceNotFoundException;
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

    @PostMapping("/tester-service/{idService}")
    public ResponseEntity<String> testerService(@PathVariable("idService") Integer idService) {
        ServiceUniversite serviceUniversite = serviceUniversiteRepository.findById(idService).orElseThrow(() -> new ResourceNotFoundException("ServiceUniversite", "idService", idService));

        if (serviceUniversite.getDisponibilite() == 0) {
            return new ResponseEntity<>("Le service est indisponible", HttpStatus.BAD_REQUEST);
        }

        //serviceUniversite.setDisponibilite(serviceUniversite.getDisponibilite() - 1);
        // serviceUniversiteRepository.save( serviceUniversite);
        else {
            return new ResponseEntity<>("le Service est encore disponible", HttpStatus.OK);
        }

    }
    @GetMapping("/total-price")
    public ResponseEntity<Float> calculateTotalPrice() {
        float totalPrice = (float) iServiceUniversiteServices.calculateTotalPrice();
        return ResponseEntity.ok(totalPrice);
    }

    @GetMapping("/totalPrice/{idService}")
    public float getTotalPriceForService(@PathVariable Integer idService) {
        ServiceUniversite service = serviceUniversiteRepository.findById(idService).get();
        return iServiceUniversiteServices.getTotalPriceForService(service);
    }
}