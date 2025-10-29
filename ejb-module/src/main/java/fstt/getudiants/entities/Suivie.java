package fstt.getudiants.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "suivie")
@Data
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SuiviId.class) //specifions la classe de cle composite
public class Suivie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_module")
    private Module module;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_etudiant")
    private Etudiant etudiant;


    @Column(name = "note")
    private Double note;

    @Column(name = "date_note")
    @Temporal(TemporalType.DATE)
    private Date dateNote;
}
