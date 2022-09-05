package it.iad.sigeba2.model;

import it.iad.sigeba2.dto.TipoContoDto;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class TipoConto implements Serializable {

    @Id
    @GeneratedValue
    private Long id;
    @Column
    private String nome;
    @Column
    private String descrizione;
    @Column
    private Double costoOperazione;
    @Column
    private Integer numeroOperazioniGratis;
    @Column
    private Double interessiAnnui;
    @Column
    private Double fido;
    @Column
    private Boolean cartaCredito;
    @Column
    private Double costoOperazioneBancomat;

    @OneToMany(mappedBy = "tipo")
    private List<ContoCorrente> contiCorrenti;

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

    public TipoConto(String nome, String descrizione, Double costoOperazione, Integer numeroOperazioniGratis, Double interessiAnnui, Double fido, Boolean cartaCredito, Double costoOperazioneBancomat, List<ContoCorrente> contiCorrenti) {
        this.nome = nome;
        this.descrizione = descrizione;
        this.costoOperazione = costoOperazione;
        this.numeroOperazioniGratis = numeroOperazioniGratis;
        this.interessiAnnui = interessiAnnui;
        this.fido = fido;
        this.cartaCredito = cartaCredito;
        this.costoOperazioneBancomat = costoOperazioneBancomat;
        this.contiCorrenti = contiCorrenti;
    }

    

}
