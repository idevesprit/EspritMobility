package tn.esprit.mobiliteinternationall.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.mobiliteinternationall.entites.Universite;
import tn.esprit.mobiliteinternationall.repositories.UniversiteRepository;
import tn.esprit.mobiliteinternationall.services.IServiceUniversite;
import tn.esprit.mobiliteinternationall.services.IServiceUniversiteIMP;

import java.util.List;

@RestController
@RequestMapping("/universite")
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class UniversiteController {
    private IServiceUniversite iServiceUniversite;

    @PostMapping("/add")
    public Universite addUniversite(@RequestBody Universite u) {
        return iServiceUniversite.addUniversite(u);
    }
@PutMapping("/update")
    public Universite updateUniversite(@RequestBody Universite u) {
        return iServiceUniversite.updateUniversite(u);
    }

@GetMapping("get/{idUniversite}")
    public Universite getUniversiteId(@PathVariable Integer idUniversite) {
        return iServiceUniversite.getUniversiteId(idUniversite);
    }

@GetMapping("/getAll")
    public List<Universite> getAllUniversite() {
        return iServiceUniversite.getAllUniversite();
    }
@DeleteMapping("/delete/{idUniversite}")
    public void removeUniversite(@PathVariable Integer idUniversite) {
        iServiceUniversite.removeUniversite(idUniversite);
    }
}
