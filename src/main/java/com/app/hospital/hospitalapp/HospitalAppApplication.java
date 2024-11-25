package com.app.hospital.hospitalapp;

import com.app.hospital.hospitalapp.entities.Patient;
import com.app.hospital.hospitalapp.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class HospitalAppApplication {

    PatientRepository patientRepository;

    public HospitalAppApplication(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(HospitalAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner start() {

        return args -> {
            Patient p1 = Patient.builder().name("Hmidani").prenom("nabil").dateNaissance(new Date()).score(10).malade(true).build();
            Patient p2 = Patient.builder().name("Filali").prenom("yassine").dateNaissance(new Date()).score(12).malade(false).build();
            Patient p3 = Patient.builder().name("elmoutkine").prenom("khalid").dateNaissance(new Date()).score(20).malade(false).build();

            Iterable<Patient> listPatients=List.of(p1,p2,p3);
            patientRepository.saveAll(listPatients);

            List<Patient> sortedPatientsList = patientRepository.findAll(Sort.by("prenom"));
            sortedPatientsList.forEach(System.out::println);
        };
    }

}
