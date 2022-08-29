package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioCancellazioneImpiegatoDto {

    private Long idImpiegato;
    private CriterioImpiegatoDto filtro = new CriterioImpiegatoDto("");
}
