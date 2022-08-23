package it.iad.sigeba2.model;

import it.iad.sigeba2.dto.LibrettoRisparmioDto;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class LibrettoRisparmio implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String codice;
    @Column
    private Double massimale;

    public LibrettoRisparmio() {
    }

    public LibrettoRisparmio(LibrettoRisparmioDto dto) {
        id = dto.getId();
        codice = dto.getCodice();
        massimale = dto.getMassimale();
    }
    
    
    
}
