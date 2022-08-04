package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioCancellazioneTipoContoDto {
    
    private Long idTipoConto;
    private CriterioTipoContoDto filtro;

}
