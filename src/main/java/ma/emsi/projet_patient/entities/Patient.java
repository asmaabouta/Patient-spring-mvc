package ma.emsi.projet_patient.entities;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Size;
import java.util.Date;
@Entity
@Table
@Data
@NoArgsConstructor @AllArgsConstructor @ToString
public class Patient {
    @Id @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Size(min = 3,max = 15,message ="Attention ,le Nom est Incorrect!!")
    private String nom;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern= "yyyy-MM-dd")
    private Date dateNaissance;
    @NotNull
    @DecimalMin(value = "4",message = "Attention , le score doit etre suppérieur ou égal a 4")
    private int score;
    private boolean malade;
}
