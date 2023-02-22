package tn.esprit.mobiliteinternationall.Services;

import tn.esprit.mobiliteinternationall.entites.ServiceUniversite;

import java.util.List;

public interface IServiceUniversiteServices {

    public ServiceUniversite addServiceUniversitee(ServiceUniversite s);

   public void removeServiceUniversite(int idService);

   public ServiceUniversite updateServiceUniversite(ServiceUniversite s);

   public ServiceUniversite getById(int idService);

  public   List<ServiceUniversite> getAllServiceUniversite();

   public void assignServiceUniversiteToCandidat(Integer idService, Integer idCandidat);

  public   void assignServiceUniversiteToUniversite(Integer idService, Integer idUniversite);


}
