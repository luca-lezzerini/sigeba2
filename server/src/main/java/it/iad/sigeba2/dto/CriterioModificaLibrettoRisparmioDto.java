package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioModificaLibrettoRisparmioDto {
    
    private LibrettoRisparmioDto librettoRisparmio;
    private CriterioLibrettoRisparmioDto filtro = new CriterioLibrettoRisparmioDto("");

    public CriterioModificaLibrettoRisparmioDto() {
    }

    public CriterioModificaLibrettoRisparmioDto(LibrettoRisparmioDto librettoRisparmio) {
        this.librettoRisparmio = librettoRisparmio;
    }

    public CriterioModificaLibrettoRisparmioDto(LibrettoRisparmioDto librettoRisparmio, CriterioLibrettoRisparmioDto filtro) {
        this.librettoRisparmio = librettoRisparmio;
        this.filtro = filtro;
    }
   
}
