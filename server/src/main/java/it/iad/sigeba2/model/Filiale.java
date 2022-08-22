package it.iad.sigeba2.model;

import it.iad.sigeba2.dto.FilialeDto;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Filiale implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String nome;
    @Column
    private String codice;

    public Filiale() {
    }

    public Filiale(FilialeDto dto) {
        id = dto.getId();
        nome = dto.getNome();
        codice = dto.getCodice();
    }
}
