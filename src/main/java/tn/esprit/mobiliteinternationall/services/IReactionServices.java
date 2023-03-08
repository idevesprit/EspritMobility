package tn.esprit.mobiliteinternationall.services;

import tn.esprit.mobiliteinternationall.entites.DislikeComment;
import tn.esprit.mobiliteinternationall.entites.LikeComment;

import javax.transaction.Transactional;

public interface IReactionServices {
    @Transactional
    LikeComment addAndAssignLikeToCommentAndCandidat(LikeComment l, Integer idCandidat, Integer idCommentaire);

    //////////////////////////////////////
    @Transactional
    DislikeComment addAndAssignDislikeToCommentAndCandidat(DislikeComment l, Integer idCandidat, Integer idCommentaire);
}
