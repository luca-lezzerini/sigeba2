package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class CriterioCancellazioneClienteDto {

    private long idCliente;
    private CriterioClienteDto filtro;
}
