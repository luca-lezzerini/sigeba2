package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioCancellazioneFilialeDto {

    private Long idFiliale;
    private CriterioFilialeDto filtro = new CriterioFilialeDto();

}
