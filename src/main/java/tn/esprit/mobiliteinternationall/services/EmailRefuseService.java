package tn.esprit.mobiliteinternationall.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import tn.esprit.mobiliteinternationall.dto.MailRequest;
import tn.esprit.mobiliteinternationall.dto.MailResponse;
import tn.esprit.mobiliteinternationall.entites.Candidat;
import tn.esprit.mobiliteinternationall.entites.Entretien;
import tn.esprit.mobiliteinternationall.repositories.CandidatRepository;
import tn.esprit.mobiliteinternationall.repositories.EntretienRepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class EmailRefuseService {

    private JavaMailSender sender;

    private Configuration config;


    EntretienRepository entretienRepository;
    CandidatRepository candidatRepository;

    public MailResponse sendEmail(MailRequest request, Map<String, Object> model) {
        List<Candidat> candidats= candidatRepository.findAll();
        for(Candidat candidat : candidats) {
            List<Entretien> listcondidat = entretienRepository.candidatAccepte(candidat.getIdCandidat());
            for (Entretien entretien : listcondidat) {



            }

        }
                MailResponse response = new MailResponse();
                MimeMessage message = sender.createMimeMessage();
                try {
                    // set mediaType
                    MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                            StandardCharsets.UTF_8.name());
                    // add attachment
                    //	helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

                    Template t = config.getTemplate("email-templateRefuse.ftl");
                    String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

                    helper.setTo(request.getTo());
                    helper.setText(html, true);
                    helper.setSubject(request.getSubject());
                    helper.setFrom(request.getFrom());
                    sender.send(message);

                    response.setMessage("mail send to : " + request.getTo());
                    response.setStatus(Boolean.TRUE);

                } catch (MessagingException | IOException | TemplateException e) {
                    response.setMessage("Mail Sending failure : " + e.getMessage());
                    response.setStatus(Boolean.FALSE);
                }


                return response;
            }



}
