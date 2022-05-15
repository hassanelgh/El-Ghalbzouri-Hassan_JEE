package com.example.app;

import com.example.app.entities.*;
import com.example.app.repositories.MedecinRepository;
import com.example.app.repositories.PatientRepository;
import com.example.app.repositories.RendezVousRepository;
import com.example.app.service.IHospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}


	@Bean
	CommandLineRunner start(IHospitalService hospitalService, PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository){
		return args -> {
			Stream.of("sarid", "samira", "bouchra").forEach(
					s -> {
						Medecin medecin=new Medecin();
						medecin.setNom(s);
						medecin.setEmail(s+"@gmail.com");
						medecin.setSpecialite(Math.random()>0.5?"Cardio":"Dantist");
						hospitalService.saveMedecin(medecin);
					}
			);

			Patient patient=new Patient();
			patient.setNom("mohemed");
			patient.setDateNaissance(new Date());
			patient.setMalade(false);
			hospitalService.savePatient(patient);

			Patient patientMohemed=patientRepository.findById(1L).orElse(null);
			Medecin medecin=medecinRepository.findByNom("samira");

			RendezVous rendezVous=new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin);
			rendezVous.setPatient(patientMohemed);
			RendezVous saveRendezVous=hospitalService.saveRDV(rendezVous);
			System.out.println(saveRendezVous.getId());
			RendezVous rendezVous1=rendezVousRepository.findAll().get(0);

			Consultation consultation=new Consultation();
			consultation.setDateConsultation(new Date());
			consultation.setRapport("rapport de la consultion ...");
			consultation.setRendezVous(rendezVous1);
			hospitalService.saveConsutlation(consultation);



		};
	}
}
