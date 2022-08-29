package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioCancellazioneMovimentoCCDto {
    
    private Long idMovimentoCC;
    private CriterioMovimentoCCDto filtro = new CriterioMovimentoCCDto();
    
    
}
