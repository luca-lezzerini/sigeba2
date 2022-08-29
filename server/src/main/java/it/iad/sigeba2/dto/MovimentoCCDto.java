package it.iad.sigeba2.dto;

import it.iad.sigeba2.model.MovimentoCC;
import lombok.Data;


@Data
public class MovimentoCCDto {
    
    private Long id;
    private String tipo;
    private String descrizione;
    
    public MovimentoCCDto() {
    }
    
    public MovimentoCCDto(MovimentoCC movimentocc){
        id = movimentocc.getId();
        tipo = movimentocc.getTipo();
        descrizione = movimentocc.getDescrizione();
    }
    
    
}
