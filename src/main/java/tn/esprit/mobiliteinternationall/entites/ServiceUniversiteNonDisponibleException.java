package tn.esprit.mobiliteinternationall.entites;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
public class ServiceUniversiteNonDisponibleException extends RuntimeException{
    public ServiceUniversiteNonDisponibleException(String message) {
        super(message);
    }
}
