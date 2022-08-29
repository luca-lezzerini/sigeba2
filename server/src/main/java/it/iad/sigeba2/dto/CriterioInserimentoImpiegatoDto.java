
package it.iad.sigeba2.dto;


public class CriterioInserimentoImpiegatoDto extends CriterioModificaImpiegatoDto{
    
public CriterioInserimentoImpiegatoDto(){
    
}

public CriterioInserimentoImpiegatoDto(ImpiegatoDto impiegato){
    super(impiegato);
}

public CriterioInserimentoImpiegatoDto(ImpiegatoDto impiegato,CriterioImpiegatoDto filtro){
    super(impiegato, filtro);
}

}
