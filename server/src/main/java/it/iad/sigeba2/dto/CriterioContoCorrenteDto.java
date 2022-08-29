package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioContoCorrenteDto {

    private String criterio = "";

    public CriterioContoCorrenteDto() {
    }

    public CriterioContoCorrenteDto(String criterio) {
        this.criterio = criterio;
    }
}
