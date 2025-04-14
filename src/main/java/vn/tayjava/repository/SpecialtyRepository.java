package vn.tayjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.tayjava.model.Specialty;

@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    boolean existsByName(String name);
}
