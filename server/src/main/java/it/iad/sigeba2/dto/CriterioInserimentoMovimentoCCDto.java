package it.iad.sigeba2.dto;

public class CriterioInserimentoMovimentoCCDto extends CriterioModificaMovimentoCCDto{
    
    public CriterioInserimentoMovimentoCCDto() {
    }
    
    public CriterioInserimentoMovimentoCCDto(MovimentoCCDto movimentocc){
        super(movimentocc);
    }
    
    public CriterioInserimentoMovimentoCCDto(MovimentoCCDto movimentocc,CriterioMovimentoCCDto filtro) {
        super(movimentocc, filtro);
    }
    
}
