package tn.esprit.mobiliteinternationall.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.mobiliteinternationall.entites.DislikeComment;
import tn.esprit.mobiliteinternationall.entites.LikeComment;
import tn.esprit.mobiliteinternationall.services.IReactionServices;

@AllArgsConstructor
@RestController
@RequestMapping("/reaction")
public class ReactionController {

    @Autowired
    IReactionServices iReactionServices;

    @PostMapping("/like/{idCandidat}/{idCommentaire}")
    public LikeComment addAndAssignLikeToCommentAndCandidat(@RequestBody LikeComment l, @PathVariable Integer idCandidat, @PathVariable Integer idCommentaire){
        return iReactionServices.addAndAssignLikeToCommentAndCandidat(l,idCandidat,idCommentaire);
    }

    @PostMapping("/dislike/{idCandidat}/{idCommentaire}")
    public DislikeComment addAndAssignDislikeToCommentAndCandidat(@RequestBody DislikeComment l, @PathVariable Integer idCandidat, @PathVariable Integer idCommentaire){
        return iReactionServices.addAndAssignDislikeToCommentAndCandidat(l,idCandidat,idCommentaire);
    }





}
