package it.iad.sigeba2.model;

import lombok.Data;

@Data
public class Cliente {

    private Long id;
    private String nome;
    private String cognome;
    private String codiceFiscale;

}