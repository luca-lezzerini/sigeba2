package it.iad.sigeba2.dto;

import it.iad.sigeba2.model.Impiegato;
import lombok.Data;

@Data
public class ImpiegatoDto {

    private Long id;
    private String nome;
    private String cognome;
    private String matricola;

    public ImpiegatoDto() {

    }

    public ImpiegatoDto(Impiegato impiegato) {
        id = impiegato.getId();
        nome = impiegato.getNome();
        cognome = impiegato.getCognome();
        matricola = impiegato.getMatricola();

    }

}
