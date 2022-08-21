package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioTipoContoDto {

    public CriterioTipoContoDto() {
    }

    public CriterioTipoContoDto(String criterio) {
        this.criterio = criterio;
    }

    private String criterio = "";
}
