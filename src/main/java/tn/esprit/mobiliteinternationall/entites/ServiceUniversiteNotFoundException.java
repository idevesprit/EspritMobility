package tn.esprit.mobiliteinternationall.entites;

public class ServiceUniversiteNotFoundException extends RuntimeException{
    public ServiceUniversiteNotFoundException(Integer idService) {
        super("Le service universitaire avec l'ID " + idService + " est introuvable.");
    }
}
