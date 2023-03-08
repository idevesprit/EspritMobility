package tn.esprit.mobiliteinternationall.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.mobiliteinternationall.entites.Candidat;
import tn.esprit.mobiliteinternationall.entites.Commentaire;
import tn.esprit.mobiliteinternationall.entites.Offre;
import tn.esprit.mobiliteinternationall.repositories.CandidatRepository;
import tn.esprit.mobiliteinternationall.repositories.CommentaireRepository;
import tn.esprit.mobiliteinternationall.repositories.OffreRepository;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

@Service
public class CommentaireServices implements ICommentaireServices {

    @Autowired
    CommentaireRepository commentaireRepository;
    @Autowired
    CandidatRepository candidatRepository;
    @Autowired
    OffreRepository offreRepository;

    private List<String> fetchBadWords() {
        List<String> badWords = new ArrayList<>();
        try {
            URL url = new URL("https://docs.google.com/spreadsheets/d/1hIEi2YG3ydav1E06Bzf2mQbGZ12kh2fe4ISgLg_UBuM/export?format=csv");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 0) {
                    badWords.add(values[0]);
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return badWords;
    }

    public String convertEmoticonsToEmoji(String text) {
        Map<String, String> emoticonMap = new HashMap<>();
        emoticonMap.put(":)", "\uD83D\uDE42");
        emoticonMap.put(":(", "\uD83D\uDE41");
        emoticonMap.put(":D", "\uD83D\uDE00");
        emoticonMap.put(":P", "\uD83D\uDE1B");
        emoticonMap.put(":(", "\uD83D\uDE41"); //visage froncé
        emoticonMap.put(":O", "\uD83D\uDE2E");//(visage surpris)
        emoticonMap.put(";)", "\uD83D\uDE09");//(visage clin d'œil)
        emoticonMap.put(":'(", "\uD83D\uDE22");// (visage en larmes)
        emoticonMap.put(":|", "\uD83D\uDE10"); //(visage neutre)
        emoticonMap.put(":$", "\uD83D\uDE33");//(visage qui rougit)
        emoticonMap.put(":-/", "\uD83D\uDE15");//(visage perplexe)
        emoticonMap.put(":-P",  "\uD83D\uDE1C");//(visage qui tire la langue)

        for (Map.Entry<String, String> entry : emoticonMap.entrySet()) {
            String pattern = Pattern.quote(entry.getKey()); // escape any special characters
            text = text.replaceAll(pattern, entry.getValue());
        }
        return text;
    }


    @Override
    public Commentaire addCommentaire(Commentaire c, Integer idCandidat, Integer idOffre) {
        Candidat candidat=candidatRepository.findById(idCandidat).orElse(null);
        Offre offre=offreRepository.findById(idOffre).orElse(null);
        c.setCandidat(candidat);
        c.setOffre(offre);
        c.setDateCommentaire(new Date());

        String commentTextWithEmoji = convertEmoticonsToEmoji(c.getLibelle());
        c.setLibelle(commentTextWithEmoji);

        List<String> badWords = fetchBadWords();
        boolean containsBadWord = false;
        for (String badWord : badWords) {
            if (c.getLibelle().toLowerCase().contains(badWord.toLowerCase())) {
                containsBadWord = true;
                break;
            }
        }
        if (containsBadWord) {
            return null;
        }
        return commentaireRepository.save(c);
    }

    @Override
    public Commentaire updateCommentaire(Commentaire c,Integer idCandidat) {
        Candidat candidat=candidatRepository.findById(idCandidat).orElse(null);
        if (c.getCandidat().equals(candidat))
        {
           List<String> badWords = fetchBadWords();
        boolean containsBadWord = false;
        for (String badWord : badWords) {
            if (c.getLibelle().toLowerCase().contains(badWord.toLowerCase())) {
                containsBadWord = true;
                break;
            }
        }
        if (containsBadWord) {
            return null;
        }
        return commentaireRepository.save(c);}
        else
            return null;
    }

    @Override
    public Commentaire getCommentaireById(Integer idCommentaire) {
        return commentaireRepository.findById(idCommentaire).orElse(null);
    }

    @Override
    public List<Commentaire> getAllCommentaire() {
        return commentaireRepository.findAll();
    }

    @Override
    public void removeCommentaire(Integer idCommentaire,Integer idCandidat) {
        Candidat candidat=candidatRepository.findById(idCandidat).orElse(null);
        if (commentaireRepository.findById(idCommentaire).orElse(null).getCandidat().equals(candidat))
        commentaireRepository.deleteById(idCommentaire);
    }

    @Override
    public void assignCommentaireToCandidat(Integer idCommentaire, Integer idCandidat) {
        Commentaire commentaire = commentaireRepository.findById(idCommentaire).get();
        Candidat candidat = candidatRepository.findById(idCandidat).get();
        commentaire.setCandidat(candidat);
        commentaireRepository.save(commentaire);
    }

//tri par date
@Override
public List<Commentaire> triComment() {
    return commentaireRepository.triComment();
}
/*
    @Override
    public Commentaire likeComment(int idCommentaire ) {
        Commentaire comment = commentaireRepository.findById(idCommentaire).orElse(null);
        comment.setLikeCommentaire(comment.getLikeCommentaire()+1);
        return commentaireRepository.save(comment);
    }

    @Override
    public Commentaire dislikeComment(int idCommentaire) {
        Commentaire comment = commentaireRepository.findById(idCommentaire).orElse(null);
        comment.setDislikeCommentaire(comment.getDislikeCommentaire()+1);
        return commentaireRepository.save(comment);
    }

 */

}
