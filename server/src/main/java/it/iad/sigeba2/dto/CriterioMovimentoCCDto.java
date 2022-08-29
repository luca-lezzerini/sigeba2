package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioMovimentoCCDto {
    
    public CriterioMovimentoCCDto(){
    }
    
    public CriterioMovimentoCCDto(String criterio){
        this.criterio = criterio;
        
    }
    private String criterio = "";
    
}
