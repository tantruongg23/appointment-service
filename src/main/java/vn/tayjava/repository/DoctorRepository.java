package vn.tayjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.tayjava.model.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}