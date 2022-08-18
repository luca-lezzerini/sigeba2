package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioContoCorrenteDto {

    public CriterioContoCorrenteDto() {
    }

    public CriterioContoCorrenteDto(String criterio) {
        this.criterio = criterio;

    }
    private String criterio = "";
}
