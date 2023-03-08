package tn.esprit.mobiliteinternationall.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.mobiliteinternationall.entites.*;
import tn.esprit.mobiliteinternationall.services.ICommentaireServices;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/commentaire")
public class CommentaireContoller {

    @Autowired
    ICommentaireServices iCommentaireServices;


   @PostMapping("/addcommentaire/{idCandidat}/{idOffre}")
   public Commentaire addCommentaire(@RequestBody Commentaire c, @PathVariable int idCandidat, @PathVariable int idOffre){
       return iCommentaireServices.addCommentaire(c,idCandidat,idOffre);}
    @PutMapping("/updatecommentaire/{idCandidat}")
    public Commentaire updateCommentaire(@RequestBody Commentaire c, @PathVariable int idCandidat) {
        return iCommentaireServices.updateCommentaire(c,idCandidat);}

    @GetMapping("/commentairesbyid/{idCommentaire}")
    public Commentaire FindCommentaireById(@PathVariable int idCommentaire) {
        return iCommentaireServices.getCommentaireById(idCommentaire);}

    @GetMapping("/allcommentaire")
    public List<Commentaire> FindAllCommentaire() {
        return iCommentaireServices.getAllCommentaire();}

    @DeleteMapping("/remove/{idCommentaire}/{idCandidat}")
    public void removeCommentaire(@PathVariable int idCommentaire,@PathVariable int idCandidat){
        iCommentaireServices.removeCommentaire(idCommentaire,idCandidat);}

    @PutMapping("/assignServiceUniversiteToUniversite/{idCommentaire}/{idCandidat}")
    public void assignCommentaireToCandidat(@PathVariable Integer idCommentaire, @PathVariable Integer idCandidat) {
        iCommentaireServices.assignCommentaireToCandidat(idCommentaire,idCandidat);}

//tri par date
    @GetMapping("/triComment")
     List<Commentaire> triComment(){
    return iCommentaireServices.triComment();}

 /*   @PutMapping("/likecomment/{idCommentaire}")
    public Commentaire likeComment(@PathVariable int idCommentaire){
        return iCommentaireServices.likeComment(idCommentaire);
    }

    @PutMapping("/dislikecomment/{idCommentaire}")
    public Commentaire dislikeComment(@PathVariable int idCommentaire){
        return iCommentaireServices.dislikeComment(idCommentaire);
    }

  */


}
