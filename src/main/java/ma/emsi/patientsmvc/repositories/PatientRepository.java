package ma.emsi.patientsmvc.repositories;

import ma.emsi.patientsmvc.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
    //Page<Patient> findByNomContains(String kw, Pageable pageable);

    Page<Patient> findByNomContainsAndScoreGreaterThanEqual(String keyword, int keyword2, Pageable pageable);
    Page<Patient> findByNomContainsAndScoreGreaterThanEqualAndCINContains(String keyword, int keyword2, String keyword3, Pageable pageable);
    Page<Patient> findByCINContains(String cin,Pageable pageable);

}
