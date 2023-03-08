package tn.esprit.mobiliteinternationall.controllers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.mobiliteinternationall.dto.*;
import tn.esprit.mobiliteinternationall.entites.Candidature;
import tn.esprit.mobiliteinternationall.entites.RoleCandidat;
import tn.esprit.mobiliteinternationall.entites.StatutCandidature;
import tn.esprit.mobiliteinternationall.repositories.CandidatureRepository;
import tn.esprit.mobiliteinternationall.services.EmailService;
import tn.esprit.mobiliteinternationall.services.ICandidatureService;
import com.itextpdf.text.pdf.PdfWriter;

import org.slf4j.Logger;

import javax.sql.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.joda.time.DateTimeZone;
@Slf4j
@RestController
@RequestMapping("/candidature")
public class CandidatureController {

    Logger LOGGER = LoggerFactory.getLogger(CandidatureController.class);
    @Autowired
    ICandidatureService iCandidatureService;
    @Autowired
    private EmailService service;
   @Autowired
    private CandidatureRepository candidatureRepository;

    @Autowired
    private DataSource dataSource;
    @PostMapping
    public Candidature addCandidature(@RequestBody Candidature candidature) {
        candidature.setStatutCandidature(StatutCandidature.EnCoursDeTraitement);
        Date currentDate = Calendar.getInstance().getTime();
        candidature.setDebutMobilite(currentDate);
        //LOGGER.info("[CandidatureController] - Inside getStudents method");
        return iCandidatureService.addCandidature(candidature);

    }

    @DeleteMapping("/{idCandidature}")
    public ResponseEntity removeCandidature(@PathVariable int idCandidature) {

        iCandidatureService.removeCandidature(idCandidature);
        return new ResponseEntity<>("Candidature a été Supprimé!", HttpStatus.OK);

    }

    @PutMapping("/{idCandidature}")
    public ResponseEntity updateCandidature(@PathVariable int idCandidature) {
        iCandidatureService.updateCandidature(idCandidature);
        return new ResponseEntity<>("Cette Candidature Accepté Pour l'Entretient", HttpStatus.OK);
    }

    @GetMapping
    public List<Candidature> findAllCandidatures() {
        return iCandidatureService.getAllCandidatures();
    }

    @GetMapping("/{idFact}")
    public Candidature findCandidatureById(@PathVariable int idCandidature) {
        return iCandidatureService.getById(idCandidature);
    }

    @GetMapping("/assignCandidatureToCandidat/{idCandidature}/{roleCandidat}")
    public ResponseEntity assignCandidatureToCandidat(@PathVariable int idCandidature, @PathVariable RoleCandidat roleCandidat) {

        iCandidatureService.assignCandidatureToCandidat(idCandidature, roleCandidat);
        return new ResponseEntity<>("Candidature Affecté", HttpStatus.OK);

    }



    @PostMapping("/sendingEmail")
    public MailResponse sendEmail(@RequestBody MailRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("Name", request.getName());
        model.put("location", "Esprit");
        return service.sendEmail(request, model);

    }



    @GetMapping("/MoyenneFilter/{moyenne1}/{moyenne2}")
    public List<Candidature> MoyenneFilter(@PathVariable Float moyenne1,@PathVariable Float moyenne2) {
        LOGGER.info("[CandidatureController] - Inside Moyenne Filter method");
        return iCandidatureService.findCandidatureByMoyenne(moyenne1,moyenne2);
    }


    @GetMapping("/countByStatutCandidature/{statutCandidature}")
    public Integer nbrCandidaturesAcceptées(@PathVariable StatutCandidature statutCandidature) {
        return iCandidatureService.nbrCandidaturesAcceptées(statutCandidature);
    }

    @PostMapping("/upload")
    public ResponseEntity ajouterCandidature(@RequestParam("Relevé de Note") MultipartFile relevéDeNote,@RequestParam("CV") MultipartFile cv) throws IOException {
        Candidature c = new Candidature();
        try {
            c.setRelevéDeNote(relevéDeNote.getBytes());
            c.setCv(cv.getBytes());

        } catch (IOException e) {
            e.printStackTrace();
        }
        c.setStatutCandidature(StatutCandidature.EnCoursDeTraitement);
        Date currentDate = Calendar.getInstance().getTime();
        c.setDebutMobilite(currentDate);
     ////////// extraction
      /*  PDDocument document = PDDocument.load(cv.getInputStream());
        PDFTextStripper stripper = new PDFTextStripper();
        Float text = Float.valueOf(stripper.getText(document));
        document.close();
        c.setMoyenne(text);*/
/////////////////////////////////////
        PDDocument document = PDDocument.load(relevéDeNote.getInputStream());
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();

        Pattern pattern = Pattern.compile("(?i)MOYENNE\\s*:\\s*(\\d+(?:\\.\\d+)?)"); // expression régulière pour trouver la valeur de "moyenne générale"
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            Float moyenne = Float.parseFloat(matcher.group(1)); // extrait la valeur trouvée et la convertit en float
            c.setMoyenne(moyenne);
            candidatureRepository.save(c);
        }

        /////////////////////////////
       // candidatureRepository.save(c);
        return new ResponseEntity<>("Candidature Ajoutée ", HttpStatus.OK);

    }

   @GetMapping("/upload/{idCandidature}")
   public ResponseEntity<byte[]> getFile(@PathVariable Integer idCandidature) {
       Optional<Candidature> fileEntity = candidatureRepository.findById(idCandidature);
       if (fileEntity.isPresent()) {
           byte[] fileContent = fileEntity.get().getRelevéDeNote();
           HttpHeaders headers = new HttpHeaders();
           headers.setContentType(MediaType.APPLICATION_PDF);
           headers.setContentLength(fileContent.length);
           headers.set("Content-Disposition", "inline; filename=" + fileEntity.get().getRelevéDeNote());
           return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
       } else {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
   }


  //  @Scheduled(fixedDelay = 60000)
    @Scheduled(cron = "0 0 0 * * *") // runs every day at midnight
    public void modifierStatutCandidature() {
        List<Candidature> candidatures = candidatureRepository.findAll();

        for (Candidature candidature : candidatures) {
            Float moyenne = candidature.getMoyenne();
            Date dateFinOffre = candidature.getOffre().getDateFinOffre();

            if (moyenne != null && moyenne <= 10f && LocalDate.now().isAfter(convertToLocalDate(dateFinOffre))) {
                candidature.setStatutCandidature(StatutCandidature.Refusé);
                candidatureRepository.save(candidature);
                log.info("La liste des Candidature refusées : " + candidature.getDebutMobilite() + " : Moyenne :"
                       + candidature.getMoyenne());

            }

        }
    }

    private LocalDate convertToLocalDate(Date date) {
        if (date == null) {
        return null;
    }
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }







    @GetMapping(value = "/generate-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePdf() throws Exception {
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, baos);
        document.open();
        addTableToDocument(document);
        document.close();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "table.pdf");
        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }

    private void addTableToDocument(Document document) throws SQLException, DocumentException {
        Chunk boldText = new Chunk("Mobilité International", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18, Font.UNDERLINE, BaseColor.RED));
        Paragraph title = new Paragraph(boldText);
        title.setAlignment(Element.ALIGN_CENTER);

        Chunk boldText1 = new Chunk("La liste des Candidatures acceptées pour l'entretien", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 16, Font.UNDERLINE, BaseColor.BLACK));
        Paragraph title1 = new Paragraph(boldText1);
        title1.setAlignment(Element.ALIGN_CENTER);
        Paragraph emptyLine1 = new Paragraph("\n");
        Paragraph emptyLine2 = new Paragraph("\n");
        Image img = null;
        try {
            img = Image.getInstance("src/main/resources/esprit.png");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        img.setAlignment(Element.ALIGN_CENTER);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        Font font = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);

        PdfPCell cell = new PdfPCell(new Phrase("Date de Mobilité", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Cin", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Nom", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Prenom", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Moyenne", font));
        table.addCell(cell);
        cell = new PdfPCell(new Phrase("Email", font));
        table.addCell(cell);
        ResultSet resultSet = dataSource.getConnection().createStatement().executeQuery("SELECT * FROM candidature INNER JOIN candidat ON candidature.candidat_id_candidat = candidat.id_candidat  WHERE candidature.statut_candidature = 'AcceptéPourEntretient'");
        while (resultSet.next()) {
            table.addCell(resultSet.getString("debut_mobilite"));
            table.addCell(resultSet.getString("cin"));
            table.addCell(resultSet.getString("nom"));
            table.addCell(resultSet.getString("prenom"));
            table.addCell(resultSet.getString("moyenne"));
            table.addCell(resultSet.getString("email"));
        }

        document.add(title);
        document.add(img);
        document.add(title1);
        document.add(emptyLine1);
        document.add(emptyLine2);
        document.add(table);
    }


    @Scheduled(fixedDelay = 60000)
   // @Scheduled(cron = "0 0 * * * *") // toutes les heures
    public void archiverCandidatures() {
        List<Candidature> candidatures = candidatureRepository.findAll();

        for (Candidature candidature : candidatures) {
            Float moyenne = candidature.getMoyenne();

            if (moyenne != null && moyenne <= 14f && moyenne >= 11f) {
                candidature.setArchive(true);
                candidatureRepository.save(candidature);
                log.info("La liste des Candidature archivées : " + candidature.getDebutMobilite() + " : Moyenne :"
                        + candidature.getMoyenne());

            }

        }


    }
}











