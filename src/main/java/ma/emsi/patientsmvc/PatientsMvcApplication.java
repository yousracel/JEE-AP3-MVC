package ma.emsi.patientsmvc;

import ma.emsi.patientsmvc.entities.Contact;
import ma.emsi.patientsmvc.entities.Patient;
import ma.emsi.patientsmvc.entities.Medecin;

import ma.emsi.patientsmvc.repositories.ContactRepository;
import ma.emsi.patientsmvc.repositories.MedecinRepository;
import ma.emsi.patientsmvc.repositories.PatientRepository;
import ma.emsi.patientsmvc.sec.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
        }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@Bean
    CommandLineRunner commandLineRunner(ContactRepository contactRepository){
        return args -> {
            contactRepository.save(
                    new Contact(new Date(),null, "APPEL", "AGENT","union","mono", "ASSISTANCE","demande info", "dkfnskn")
            );
            contactRepository.findAll().forEach(p->{
                System.out.println(p.getNomInterm());
            });
        };
    }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(
                    new Patient(null,"khadija","Mme",new Date(),false,10,"BE45481"));


            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });

        };
    }

   //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {

            //add Users
            /*securityService.saveNewUser("mohamed","1234","1234");
            securityService.saveNewUser("yassine","1234","1234");
            securityService.saveNewUser("hassan","1234","1234");*/

            securityService.saveNewUser("khadija","1111","1111");

            //add Roles
            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            //attribute role to users
            /*securityService.addRoleToUser("mohamed","USER");
            securityService.addRoleToUser("mohamed","ADMIN");
            securityService.addRoleToUser("yassine","USER");
            securityService.addRoleToUser("hassan","USER");*/

            securityService.addRoleToUser("khadija","ADMIN");
            //securityService.addRoleToUser("khadija","USER");
        };
    }

    //@Bean
    /*CommandLineRunner commandLineRunner(MedecinRepository medecinRepository) {
        return args -> {
            medecinRepository.save(new Medecin(null, "Hassan", new Date(),"traumato"));
            medecinRepository.save(new Medecin(null, "Mohammed", new Date(),"pediatre"));
            medecinRepository.save(new Medecin(null, "Yassine", new Date(),"orl"));
            medecinRepository.save(new Medecin(null, "Hanae", new Date(),"encologue"));
            medecinRepository.findAll().forEach(p -> {
                System.out.println(p.getNom());
            });
        };
    }*/

}
