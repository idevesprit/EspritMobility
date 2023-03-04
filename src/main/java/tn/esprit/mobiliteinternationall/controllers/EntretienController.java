package tn.esprit.mobiliteinternationall.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.mobiliteinternationall.dto.MailRequest;
import tn.esprit.mobiliteinternationall.dto.MailResponse;
import tn.esprit.mobiliteinternationall.entites.Entretien;
import tn.esprit.mobiliteinternationall.entites.StatutEntretien;
import tn.esprit.mobiliteinternationall.repositories.EntretienRepository;
import tn.esprit.mobiliteinternationall.services.EmailFelicitationService;
import tn.esprit.mobiliteinternationall.services.EmailRefuseService;
import tn.esprit.mobiliteinternationall.services.IServiceEntretien;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/entretien")
@AllArgsConstructor

public class EntretienController {

    private IServiceEntretien iServiceEntretien;
    private final EntretienRepository entretienRepository;
    private  final EmailFelicitationService emailFelicitationService;
    private  final EmailRefuseService emailRefuseService;

    @PostMapping("/add")
    public Entretien addEntretien(@RequestBody Entretien E) {
        E.setStatutEntretien(StatutEntretien.EnAttente);
        return iServiceEntretien.addEntretien(E);
    }

    @PutMapping("/update")
    public Entretien updateEntretien(@RequestBody Entretien E) {
        return iServiceEntretien.updateEntretien(E);
    }

   @GetMapping("/get/{idEntretien}")
    public Entretien getEntretienId(@PathVariable Integer idEntretien) {
        return iServiceEntretien.getEntretienId(idEntretien);
    }

   @GetMapping("/getAll")
    public List<Entretien> getAllEntretien() {
        return iServiceEntretien.getAllEntretien();
    }
@DeleteMapping("/delete/{idEntretien}")
    public void removeEntretien(Integer idEntretien) {
        iServiceEntretien.removeEntretien(idEntretien);
    }
    @PostMapping("add/{idCandidature}/{idUniversite}")
    public void addEntretienAndAssignCandidatureAndUniversite(@RequestBody Entretien E,@PathVariable int idCandidature,@PathVariable int idUniversite) {
          iServiceEntretien.addEntretienAndAssignCandidatureAndUniversite(E,idCandidature,idUniversite);

    }
    @PostMapping("/sendEmailFelicitation")
    public MailResponse sendEmailFelicitation(@RequestBody MailRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("Name", request.getName());
        model.put("location", "EspritMobility");
        return emailFelicitationService.sendEmail(request, model);

    }
    @PostMapping("/sendingEmailRefuse")
    public MailResponse sendEmailResuse(@RequestBody MailRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("Name", request.getName());
        model.put("location", "EspritMobility");
        return emailRefuseService.sendEmail(request, model);

    }

}



