package ma.emsi.patientsmvc;

import ma.emsi.patientsmvc.entities.Patient;
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
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(
                    new Patient(null,"yousra","Mme",new Date(),false,10,"BE45481"));
            patientRepository.save(
                    new Patient(null,"sabah","Mme",new Date(),true,12,"BE44881"));
            patientRepository.save(
                    new Patient(null,"alia","Mme",new Date(),true,100,"BE41081"));
            patientRepository.save(
                    new Patient(null,"othmane","Mr",new Date(),false,70,"BE49981"));

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });

        };
    }

    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {

            //add Users
            securityService.saveNewUser("mohamed","1234","1234");
            securityService.saveNewUser("yassine","1234","1234");
            securityService.saveNewUser("hassan","1234","1234");

            //add Roles
            securityService.saveNewRole("USER","");
            securityService.saveNewRole("ADMIN","");

            //attribute role to users
            securityService.addRoleToUser("mohamed","USER");
            securityService.addRoleToUser("mohamed","ADMIN");
            securityService.addRoleToUser("yassine","USER");
            securityService.addRoleToUser("hassan","USER");

        };
    }

}
