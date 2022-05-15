package com.example.app.service;


import com.example.app.entities.Consultation;
import com.example.app.entities.Medecin;
import com.example.app.entities.Patient;
import com.example.app.entities.RendezVous;
import org.springframework.stereotype.Service;

public interface IHospitalService {
    Patient savePatient(Patient patient);
    Medecin saveMedecin(Medecin medecin);
    RendezVous saveRDV(RendezVous rendezVous);
    Consultation saveConsutlation(Consultation consultation);
}
