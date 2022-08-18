package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioCancellazioneClienteDto {

    private Long idCliente;
    private CriterioClienteDto filtro = new CriterioClienteDto();
}
