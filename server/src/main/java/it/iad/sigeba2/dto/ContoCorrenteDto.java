package it.iad.sigeba2.dto;

import it.iad.sigeba2.model.ContoCorrente;
import lombok.Data;

@Data
public class ContoCorrenteDto {

    private Long id;
    private String iban;
    private Double fido;

    public ContoCorrenteDto() {
    }

    public ContoCorrenteDto(ContoCorrente contoCorrente) {
        id = contoCorrente.getId();
        iban = contoCorrente.getIban();
        fido = contoCorrente.getFido();
    }

}
