package vn.tayjava.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.tayjava.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByPhone(String phone);

}
