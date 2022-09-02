package it.iad.sigeba2.model;

import it.iad.sigeba2.dto.ClienteDto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Cliente implements Serializable{

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String nome;
    @Column
    private String cognome;
    @Column
    private String codiceFiscale;
    
    private List<ContoCorrente> contiCorrenti;

    public Cliente() {
    }

    public Cliente(ClienteDto dto) {
        id = dto.getId();
        nome = dto.getNome();
        cognome = dto.getCognome();
        codiceFiscale = dto.getCodiceFiscale();
    }

}
