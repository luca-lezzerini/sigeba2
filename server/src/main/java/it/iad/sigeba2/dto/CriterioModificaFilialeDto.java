package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioModificaFilialeDto {

    private FilialeDto filiale;
    private CriterioFilialeDto filtro = new CriterioFilialeDto("");

    public CriterioModificaFilialeDto() {
    }

    public CriterioModificaFilialeDto(FilialeDto filiale) {
        this.filiale = filiale;
    }

    public CriterioModificaFilialeDto(FilialeDto filiale, CriterioFilialeDto filtro) {
        this.filiale = filiale;
        this.filtro = filtro;
    }
}
