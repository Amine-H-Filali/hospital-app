package com.app.hospital.hospitalapp.repositories;

import com.app.hospital.hospitalapp.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
