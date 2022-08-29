package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioModificaImpiegatoDto {

    private ImpiegatoDto impiegato;
    CriterioImpiegatoDto filtro = new CriterioImpiegatoDto("");

    public CriterioModificaImpiegatoDto() {

    }

    public CriterioModificaImpiegatoDto(ImpiegatoDto impiegato) {
        this.impiegato = impiegato;
    }

    public CriterioModificaImpiegatoDto(ImpiegatoDto impiegato, CriterioImpiegatoDto filtro) {

        this.impiegato = impiegato;
        this.filtro = filtro;
    }

}
