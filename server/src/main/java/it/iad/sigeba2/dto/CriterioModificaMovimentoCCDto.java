package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioModificaMovimentoCCDto {
    
    private MovimentoCCDto movimentocc;
    private CriterioMovimentoCCDto filtro = new CriterioMovimentoCCDto("");
    
    public CriterioModificaMovimentoCCDto(){
    }
    
    public CriterioModificaMovimentoCCDto(MovimentoCCDto movimentocc){
        this.movimentocc = movimentocc;
    }
    
    public CriterioModificaMovimentoCCDto(MovimentoCCDto movimentocc,CriterioMovimentoCCDto filtro){
        this.movimentocc = movimentocc;
        this.filtro = filtro;
    }
    
}
