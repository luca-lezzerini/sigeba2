package it.iad.sigeba2.model;

import it.iad.sigeba2.dto.ImpiegatoDto;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Impiegato implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String nome;
    @Column
    private String cognome;
    @Column
    private String matricola;

    public Impiegato() {

    }

    public Impiegato(ImpiegatoDto dto) {
        id = dto.getId();
        nome = dto.getNome();
        cognome = dto.getCognome();
        matricola = dto.getMatricola();

    }

}
