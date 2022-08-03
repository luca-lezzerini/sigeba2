package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioClienteDto {

    public CriterioClienteDto() {
    }

    public CriterioClienteDto(String criterio) {
        this.criterio = criterio;
    }

    private String criterio = "";
    
    
}
