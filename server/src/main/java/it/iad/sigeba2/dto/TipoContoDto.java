package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class TipoContoDto {

    private Long id;
    private String nome;
    private String descrizione;
    private Double costoOperazione;
    private Integer numeroOperazioniGratis;
    private Double interessiAnnui;
    private Double fido;
    private Boolean cartaCredito;
    private Double costoOperazioneBancomat;
    

    public TipoContoDto() {
    }

    

}
