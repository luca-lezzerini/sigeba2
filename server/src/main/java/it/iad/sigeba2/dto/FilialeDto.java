package it.iad.sigeba2.dto;

import it.iad.sigeba2.model.Filiale;
import lombok.Data;

@Data
public class FilialeDto {

    private Long id;
    private String nome;
    private String codice;

    public FilialeDto() {
    }

    public FilialeDto(Filiale filiale) {
        id = filiale.getId();
        nome = filiale.getNome();
        codice = filiale.getCodice();
    }

}
