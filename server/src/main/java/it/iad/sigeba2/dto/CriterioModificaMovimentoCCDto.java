package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioModificaMovimentoCCDto {
    
    private MovimentoCCDto movimentoCc;
    private CriterioMovimentoCCDto filtro = new CriterioMovimentoCCDto("");
    
    public CriterioModificaMovimentoCCDto(){
    }
    
    public CriterioModificaMovimentoCCDto(MovimentoCCDto movimentocc){
        this.movimentoCc = movimentocc;
    }
    
    public CriterioModificaMovimentoCCDto(MovimentoCCDto movimentocc,CriterioMovimentoCCDto filtro){
        this.movimentoCc = movimentocc;
        this.filtro = filtro;
    }
    
}
