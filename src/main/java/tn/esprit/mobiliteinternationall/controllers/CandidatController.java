package com.supportportal.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.supportportal.domain.Candidat;
import com.supportportal.service.ServiceCandidat;


@Controller
@RequestMapping(path = { "/", "/candidat"})
public class CandidatController {

	@Autowired
	ServiceCandidat serviceCandidat ;
	
	
	 //http://localhost:8081/add-Candidat
    @PostMapping("/add-Candidat")
    @ResponseBody
    public Candidat addCandidat (@RequestBody Candidat r){

    	serviceCandidat.addCandidat(r);
        return r;
    } 	
    
 // http://localhost:8081/retrieve-all-Candidats
 	@GetMapping("/retrieve-all-Candidats")
 	@ResponseBody
 	public List<Candidat> getCandidats() {
 		List<Candidat> list = serviceCandidat.getAllCandidats();
 		return list;
 	}

 	

 	// http://localhost:8081/remove-Candidat/{idCandidat}
 	@DeleteMapping("/remove-Candidat/{idCandidat}")
 	@ResponseBody
 	public void removeReclamation(@PathVariable("idCandidat") int idCandidat) {
 		serviceCandidat.removeCandidat(idCandidat);
 	}
 	
 // http://localhost:8081/retrieve-Candidat/{id}
 	@GetMapping("/retrieve-Candidat/{id}")
 	@ResponseBody
 	public Candidat retrieveCandidat(@PathVariable("id") int id) {
 		return serviceCandidat.getCandidatId(id);
 	}

 	// http://localhost:8081/modify-Candidat/{idCandidat}
 	@PutMapping("/modify-Candidat/{idCandidat}")
 	@ResponseBody
 	public Candidat updateCandidat(@RequestBody Candidat r) {
 		return serviceCandidat.updateCandidat(r);
 	}
}
