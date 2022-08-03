package it.iad.sigeba2.model;

import it.iad.sigeba2.dto.ClienteDto;
import lombok.Data;

@Data
public class Cliente {

    private Long id;
    private String nome;
    private String cognome;
    private String codiceFiscale;

    public Cliente() {
    }

    public Cliente(ClienteDto dto) {
        id = dto.getId();
        nome = dto.getNome();
        cognome = dto.getCognome();
        codiceFiscale = dto.getCodiceFiscale();
    }

}
