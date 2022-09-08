package it.iad.sigeba2.dto;

import lombok.Data;

@Data
public class IdAssociazioneDto {
    
    private Long idCliente;
    private Long idConto;
    private Long idTipoConto;
    private Long idFiliale;
}
