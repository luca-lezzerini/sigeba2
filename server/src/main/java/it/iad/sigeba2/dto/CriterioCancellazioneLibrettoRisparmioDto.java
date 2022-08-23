package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioCancellazioneLibrettoRisparmioDto {
    
    private Long idLibrettoRisparmio;
    private CriterioLibrettoRisparmioDto filtro = new CriterioLibrettoRisparmioDto();
    
}
