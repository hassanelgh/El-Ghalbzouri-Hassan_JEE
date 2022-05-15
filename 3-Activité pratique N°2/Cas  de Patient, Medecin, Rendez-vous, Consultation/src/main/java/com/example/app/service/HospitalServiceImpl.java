package com.example.app.service;

import com.example.app.entities.Consultation;
import com.example.app.entities.Medecin;
import com.example.app.entities.Patient;
import com.example.app.entities.RendezVous;
import com.example.app.repositories.ConsultationRepository;
import com.example.app.repositories.MedecinRepository;
import com.example.app.repositories.PatientRepository;
import com.example.app.repositories.RendezVousRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
@AllArgsConstructor
public class HospitalServiceImpl implements IHospitalService {
    private PatientRepository patientRepository;
    private MedecinRepository medecinRepository;
    private RendezVousRepository rendezVousRepository;
    private ConsultationRepository consultationRepository;
    @Override
    public Patient savePatient(Patient patient) {

        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRDV(RendezVous rendezVous) {
        rendezVous.setId(UUID.randomUUID().toString());
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsutlation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
}
