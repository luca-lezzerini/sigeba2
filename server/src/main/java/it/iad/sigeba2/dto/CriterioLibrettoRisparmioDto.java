package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioLibrettoRisparmioDto {
    
    public CriterioLibrettoRisparmioDto(){}
    
    public CriterioLibrettoRisparmioDto (String criterio){
        this.criterio = criterio;
    }
    
    private String criterio = "";
    
}
