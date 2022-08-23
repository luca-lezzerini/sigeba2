package it.iad.sigeba2.dto;

public class CriterioInserimentoLibrettoRisparmioDto extends CriterioModificaLibrettoRisparmioDto{
    
    public CriterioInserimentoLibrettoRisparmioDto(){}
    
    public CriterioInserimentoLibrettoRisparmioDto(LibrettoRisparmioDto librettoRisparmio){
        super(librettoRisparmio);
    }
  
    public CriterioInserimentoLibrettoRisparmioDto(LibrettoRisparmioDto librettoRisparmio, CriterioLibrettoRisparmioDto filtro){
        super(librettoRisparmio, filtro);
    }
    
}
