package tn.esprit.mobiliteinternationall.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.mobiliteinternationall.entites.Reclamation;
import tn.esprit.mobiliteinternationall.repositories.ReclamationRepository;
import tn.esprit.mobiliteinternationall.services.ReclamationService;


@RestController
@RequestMapping(path = { "/", "/user"})
public class ReclamationController {
    @Autowired
    ReclamationService reclamationService;
    
    @Autowired
    ReclamationRepository reclamationRepository;

    //http://localhost:8081/add-Reclamation
    @PostMapping("/add-Reclamation")
    @ResponseBody
    public Reclamation addReclamation (@RequestBody Reclamation r){
        reclamationService.addReclamation(r);
        return r;
    } 
    
    //http://localhost:8081/retrieve-Reclamation/{id}
 	@GetMapping("/retrieve-Reclamation/{id}")
 	@ResponseBody
 	public Reclamation retrieveReclamation(@PathVariable("id") int id) {
 		return reclamationService.retrieveReclamation(id);
 	}
 	
    //http://localhost:8081/retrieve-all-Reclamations
 	@GetMapping("/retrieve-all-Reclamations")
 	@ResponseBody
 	public List<Reclamation> getCReclamations() {
 		List<Reclamation> list = reclamationService.getallReclamations();
 		return list;
 	} 
 	
 	//http://localhost:8081/update-Reclamation/{idReclamation}
  	@PutMapping("/update-Reclamation/{idReclamation}")
  	@ResponseBody
  	public Reclamation updateReclamation(@PathVariable("idReclamation") int idReclamation, @RequestBody Reclamation reclamation){
        reclamationService.updateReclamation(idReclamation, reclamation);
        return reclamation;
  	}
 
 	//http://localhost:8081/remove-Reclamation/{idReclamation}
 	@DeleteMapping("/remove-Reclamation/{idReclamation}")
 	@ResponseBody
 	public void removeReclamation(@PathVariable("idReclamation") int idReclamation) {
 		reclamationService.deleteReclamation(idReclamation);
 	}
 	
}