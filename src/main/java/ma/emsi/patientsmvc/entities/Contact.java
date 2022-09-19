package ma.emsi.patientsmvc.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Contact {
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Enumerated(EnumType.STRING)
    private String type;
    //@Enumerated(EnumType.STRING)
    private String intermediaire;
    private String nomInterm;
    private String perimetre;
    //private List<SourceAppel> sourceAppels;
    //private List<Perimetre> perimetres;
    //@Enumerated(EnumType.STRING)
    private String typeFeedback;
    private String natureDemande;
    private String description;
}
