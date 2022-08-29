package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioModificaImpiegatoDto {

public CriterioModificaImpiegatoDto(){
    
}
public CriterioModificaImpiegatoDto(ImpiegatoDto impiegato){
    this.impiegato = impiegato;
}

public CriterioModificaImpiegatoDto(ImpiegatoDto impiegato,CriterioImpiegatoDto filtro){
    
    this.impiegato = impiegato;
    this.filtro = filtro;
}
private ImpiegatoDto impiegato;
CriterioImpiegatoDto filtro;




}
