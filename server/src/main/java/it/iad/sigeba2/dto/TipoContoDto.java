package it.iad.sigeba2.dto;

import it.iad.sigeba2.model.TipoConto;
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

      public TipoContoDto(TipoConto tipoConto) {
        id = tipoConto.getId();
        nome = tipoConto.getNome();
        descrizione = tipoConto.getDescrizione();
        costoOperazione = tipoConto.getCostoOperazione();
        numeroOperazioniGratis = tipoConto.getNumeroOperazioniGratis();
        interessiAnnui = tipoConto.getInteressiAnnui();
        fido = tipoConto.getFido();
         cartaCredito = tipoConto.getCartaCredito();
         costoOperazioneBancomat = tipoConto.getCostoOperazioneBancomat();
      
    }

}
