package it.iad.sigeba2.dto;

import it.iad.sigeba2.model.LibrettoRisparmio;
import lombok.Data;

@Data
public class LibrettoRisparmioDto {
    
    private Long id;
    private String codice;
    private Double massimale;

    public LibrettoRisparmioDto() {
    }
    
    public LibrettoRisparmioDto (LibrettoRisparmio librettoRisparmio){
        id = librettoRisparmio.getId();
        codice = librettoRisparmio.getCodice();
        massimale = librettoRisparmio.getMassimale();
    }
    
    
}
