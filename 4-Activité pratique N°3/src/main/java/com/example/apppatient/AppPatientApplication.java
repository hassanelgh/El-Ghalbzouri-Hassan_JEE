package com.example.apppatient;

import com.example.apppatient.entities.Patient;
import com.example.apppatient.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class AppPatientApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppPatientApplication.class, args);
	}

	//@Bean
	CommandLineRunner commandLineRunner(PatientRepository patientRepository){
		return args -> {
			patientRepository.save(new Patient(null,"hassan",new Date(),false,12));
			patientRepository.save(new Patient(null,"mohemmed",new Date(),true,10));
			patientRepository.save(new Patient(null,"hanae",new Date(),false,1));
			patientRepository.save(new Patient(null,"layla",new Date(),true,120));


			patientRepository.findAll().forEach(patient -> {
				System.out.println(patient.getNom());
			});


		};
	}
}
