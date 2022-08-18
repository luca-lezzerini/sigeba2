package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioCancellazioneContoCorrenteDto {

    private Long idContoCorrente;
    private CriterioContoCorrenteDto filtro = new CriterioContoCorrenteDto();

}
