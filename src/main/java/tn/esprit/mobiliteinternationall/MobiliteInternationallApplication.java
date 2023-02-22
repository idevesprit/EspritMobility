package tn.esprit.mobiliteinternationall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.mobiliteinternationall.dto.MailRequest;
import tn.esprit.mobiliteinternationall.dto.MailResponse;
import tn.esprit.mobiliteinternationall.services.EmailService;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class MobiliteInternationallApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobiliteInternationallApplication.class, args);
	}

}
