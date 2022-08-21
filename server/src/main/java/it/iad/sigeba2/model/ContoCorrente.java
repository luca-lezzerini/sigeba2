package it.iad.sigeba2.model;

import it.iad.sigeba2.dto.ContoCorrenteDto;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class ContoCorrente implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String iban;
    @Column
    private Double fido;

    public ContoCorrente() {
    }

    public ContoCorrente(ContoCorrenteDto dto) {
        id = dto.getId();
        iban = dto.getIban();
        fido = dto.getFido();
    }
}
