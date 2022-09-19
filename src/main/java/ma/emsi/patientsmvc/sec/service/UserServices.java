package ma.emsi.patientsmvc.sec.service;

import ma.emsi.patientsmvc.entities.Contact;
import ma.emsi.patientsmvc.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServices {
    @Autowired
        private ContactRepository repo;
        public List<Contact> listAll(){
            return repo.findAll(Sort.by("date")
            );
        }
}
