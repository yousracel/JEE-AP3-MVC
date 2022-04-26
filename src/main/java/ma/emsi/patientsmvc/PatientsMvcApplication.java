package ma.emsi.patientsmvc;

import ma.emsi.patientsmvc.entities.Patient;
import ma.emsi.patientsmvc.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class PatientsMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientsMvcApplication.class, args);
        }

    //@Bean
    CommandLineRunner commandLineRunner(PatientRepository patientRepository){
        return args -> {
            patientRepository.save(
                    new Patient(null,"yousra",new Date(),false,10));
            patientRepository.save(
                    new Patient(null,"sabah",new Date(),true,12));
            patientRepository.save(
                    new Patient(null,"ali",new Date(),true,50));
            patientRepository.save(
                    new Patient(null,"oth",new Date(),false,70));

            patientRepository.findAll().forEach(p->{
                System.out.println(p.getNom());
            });

        };
    }

}
