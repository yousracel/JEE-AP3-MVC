package ma.emsi.patientsmvc.repositories;


import ma.emsi.patientsmvc.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public interface ContactRepository extends JpaRepository<Contact,Long>
{
        Page<Contact> findByIntermediaireContains(String keyword, Pageable pageable);
        Page<Contact> findByNomIntermContainsOrTypeContainsOrIntermediaireContains(String kw, String kw2, String kw3, Pageable pageable);

        @Query(value = "SELECT * FROM contact WHERE date >= ?1 AND date <= ?2",
                nativeQuery = true)
        Page<Contact> findAllByDate(Date startDate, Date endDate, Pageable pageable);
}
