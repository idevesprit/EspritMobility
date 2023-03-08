package tn.esprit.mobiliteinternationall.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.mobiliteinternationall.entites.ServiceUniversite;
import tn.esprit.mobiliteinternationall.entites.TypeService;

import java.util.List;

public interface ServiceUniversiteRepository extends JpaRepository<ServiceUniversite,Integer> {
    @Query("SELECT sum(e.priceService)  from ServiceUniversite e where  e.idService= :id ")
    Float sum(@Param(("id")) Integer idService);

    @Query("SELECT sum(i.priceService) from ServiceUniversite  i where  i.typeService= :id")
    Float typeService(@Param(("id")) TypeService typeService);

   // @Query("SELECT sum1(i.priceService)  from ServiceUniversite i where  i.disponibilite= :disponibilite")
   // Float sum1(@Param(("disponibilite")) Integer disponibilite);
}



//where (e.candidat)='idCandidat'





