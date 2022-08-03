package it.iad.sigeba2.model;

import lombok.Data;

@Data
public class TipoConto {
    
    private Long id;
    private String nome;
    private String descrizione;
    private Double costoOperazione;
    private Integer numeroOperazioniGratis;
    private Double interessiAnnui;
    private Double fido;
    private Boolean cartaCredito;
    private Double costoOperazioneBancomat;
    

    public TipoConto() {
    }

    
}
