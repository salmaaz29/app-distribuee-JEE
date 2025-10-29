package fstt.ma.appdistribuee.entities;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class SuiviId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long module;
    private Long etudiant;

    // constructeur par defaut
    public SuiviId() {}

    // constructeur avec parmetres
    public SuiviId(Long module, Long etudiant) {
        this.module = module;
        this.etudiant = etudiant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuiviId suiviId = (SuiviId) o;
        return Objects.equals(module,suiviId.module) && Objects.equals(etudiant,suiviId.etudiant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(module, etudiant);
    }

}
