package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class ClienteDto {
    private Long id;
    private String nome;
    private String cognome;
    private String codiceFiscale;
}
