package it.iad.sigeba2.dto;

import it.iad.sigeba2.model.Cliente;
import lombok.Data;

@Data
public class ClienteDto {

    private Long id;
    private String nome;
    private String cognome;
    private String codiceFiscale;

    public ClienteDto() {
    }

    public ClienteDto(Cliente cliente) {
        id = cliente.getId();
        nome = cliente.getNome();
        cognome = cliente.getCognome();
        codiceFiscale = cliente.getCodiceFiscale();
    }

}
