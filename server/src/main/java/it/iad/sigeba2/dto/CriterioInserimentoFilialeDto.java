package it.iad.sigeba2.dto;

public class CriterioInserimentoFilialeDto extends CriterioModificaFilialeDto{

    public CriterioInserimentoFilialeDto() {
    }

    public CriterioInserimentoFilialeDto(FilialeDto filiale) {
        super(filiale);
    }

    public CriterioInserimentoFilialeDto(FilialeDto filiale, CriterioFilialeDto filtro) {
        super(filiale, filtro);
    }

}
