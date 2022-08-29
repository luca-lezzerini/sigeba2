package it.iad.sigeba2.helper;

import it.iad.sigeba2.dto.StatoRisposta;
import it.iad.sigeba2.enumerated.GravitaStatoEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SigebaStateCollector {

    private static final ThreadLocal<List<StatoRisposta>> contestoStati = new ThreadLocal<>();

    /**
     * Aggiunge un messaggio di stato in contestoStati con 
     * @param messaggio Stringa che descrive la tipologia di segnalazione
     * @param azione Stringa che descrive l'eventuale azione correttiva, se si tratta di un errore
     * @param gravita Enum che ci descrive la gravità dell'eventuale errore
     */
    public static void addStatusMessage(String messaggio, String azione, GravitaStatoEnum gravita) {
        StatoRisposta stato = new StatoRisposta(messaggio, azione, gravita);
        List<StatoRisposta> statiPrecedenti = contestoStati.get();
        if (statiPrecedenti == null) {
            statiPrecedenti = new ArrayList<>();
        }
        statiPrecedenti.add(stato);
        contestoStati.set(statiPrecedenti);
    }

    /**
     * 
     * @return 
     */
    public static List<StatoRisposta> getAndClean() {
        List<StatoRisposta> lista = contestoStati.get();
        if (lista == null) {
            lista = Collections.emptyList();
        }
        contestoStati.remove();
        return lista;
    }
}
