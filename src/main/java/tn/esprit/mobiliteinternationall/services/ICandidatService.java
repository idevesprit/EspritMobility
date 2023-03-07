package com.supportportal.service;

import java.util.List;

import com.supportportal.domain.Candidat;



public interface ICandidatService {

	public Candidat addCandidat (Candidat E) ;
    public Candidat updateCandidat(Candidat E) ;
    public Candidat getCandidatId (Integer id) ;
    public List<Candidat> getAllCandidats();
    public void removeCandidat (Integer id);
}
