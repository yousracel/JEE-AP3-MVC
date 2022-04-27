package ma.emsi.patientsmvc.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min=4,max = 40)
    private String nom;
    private String civil;
    @Temporal(TemporalType.DATE)
   @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateNaisssance;
    private boolean malade;
    private int score;
    private String CIN;

}
