package ma.emsi.projet_patient.repositories;

import ma.emsi.projet_patient.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface PatientRepository extends JpaRepository<Patient,Long> {
   // public Page<Patient> findByNomContains(String name, Pageable pageable);
    public List<Patient> findByMalade(Boolean b);
    public List<Patient> findByNomContainsAndMalade(String name,Boolean malade);
    public Page<Patient> findByNomContains(String mc,Pageable pageable);
}
