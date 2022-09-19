package ma.emsi.patientsmvc.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class SourceAppel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;
    private String RaisonSociale;
    private String Gerant;
    private String ResponsableCommercial;
   // @Enumerated(EnumType.STRING)
    private String intermediaire;
    private String Segment;
    private String Ville;
    private String Adresse;
    private Long Telephone;
}
