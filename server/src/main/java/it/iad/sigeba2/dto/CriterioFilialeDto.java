package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioFilialeDto {

    public CriterioFilialeDto() {
    }

    public CriterioFilialeDto(String criterio) {
        this.criterio = criterio;

    }
    private String criterio = "";
}
