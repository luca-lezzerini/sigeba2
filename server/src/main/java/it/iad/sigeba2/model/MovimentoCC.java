
package it.iad.sigeba2.model;

import it.iad.sigeba2.dto.MovimentoCCDto;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class MovimentoCC implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String tipo;
    @Column
    private String descrizione;
      
    public MovimentoCC() {
    }
    
    public MovimentoCC (MovimentoCCDto dto) {
        id= dto.getId();
        tipo= dto.getTipo();
        descrizione= dto.getDescrizione();
    }
    
    
    
}
