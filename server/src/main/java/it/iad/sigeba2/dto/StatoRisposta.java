package it.iad.sigeba2.dto;

import it.iad.sigeba2.enumerated.GravitaStatoEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class StatoRisposta {

    private String messaggio;
    private String azione;
    private GravitaStatoEnum gravita;

    public StatoRisposta() {
    }

    public StatoRisposta(String messaggio, String azione, GravitaStatoEnum gravita) {
        this.messaggio = messaggio;
        this.azione = azione;
        this.gravita = gravita;
    }

}
