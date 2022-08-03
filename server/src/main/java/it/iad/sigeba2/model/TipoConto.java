package it.iad.sigeba2.model;

import it.iad.sigeba2.dto.TipoContoDto;
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

    public TipoConto(TipoContoDto dto) {
        id = dto.getId();
        nome = dto.getNome();
        descrizione = dto.getDescrizione();
        costoOperazione = dto.getCostoOperazione();
        numeroOperazioniGratis = dto.getNumeroOperazioniGratis();
        interessiAnnui = dto.getInteressiAnnui();
        fido = dto.getFido();
         cartaCredito = dto.getCartaCredito();
         costoOperazioneBancomat = dto.getCostoOperazioneBancomat();
      
    }

}
