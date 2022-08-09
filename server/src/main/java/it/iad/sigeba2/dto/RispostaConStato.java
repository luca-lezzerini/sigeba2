package it.iad.sigeba2.dto;

import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class RispostaConStato<T> {

    private T dati;
    private List<StatoRisposta> stato;

    public RispostaConStato() {
    }

    public RispostaConStato(T dati) {
        this.dati = dati;
    }

    public RispostaConStato(T dati, List<StatoRisposta> stato) {
        this.dati = dati;
        this.stato = stato;
    }

}
