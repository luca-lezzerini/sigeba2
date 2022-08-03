package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioModificaTipoContoDto {

    private TipoContoDto tipoConto;
    private CriterioTipoContoDto filtro = new CriterioTipoContoDto("");

    public CriterioModificaTipoContoDto() {
    }
    
    public CriterioModificaTipoContoDto(TipoContoDto tipoConto) {
        this.tipoConto = tipoConto;
    }

    public CriterioModificaTipoContoDto(TipoContoDto tipoConto, CriterioTipoContoDto filtro) {
        this.tipoConto = tipoConto;
        this.filtro = filtro;
    }
}
