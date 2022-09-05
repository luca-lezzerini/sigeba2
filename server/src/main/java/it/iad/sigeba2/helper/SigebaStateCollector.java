package it.iad.sigeba2.helper;

import it.iad.sigeba2.dto.StatoRisposta;
import it.iad.sigeba2.enumerated.MessaggioStatoEnum;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SigebaStateCollector {

    private static final ThreadLocal<List<StatoRisposta>> contestoStati = new ThreadLocal<>();

    /**
     * Aggiunge un messaggio di stato in contestoStati con 
     * @param messaggio Enumerato che descrive la tipologia di segnalazione
     */
    public static void addStatusMessage(MessaggioStatoEnum messaggio) {
        StatoRisposta stato = new StatoRisposta(
                messaggio.getMessaggio(), 
                messaggio.getAzione(), 
                messaggio.getGravita());
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
