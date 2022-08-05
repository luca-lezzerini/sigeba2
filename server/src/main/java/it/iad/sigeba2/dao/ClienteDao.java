package it.iad.sigeba2.dao;

import it.iad.sigeba2.model.Cliente;
import java.util.List;

public interface ClienteDao {

    /**
     * Restituisce l'insieme completo dei clienti nel DB
     *
     * @return la lista con tutti i clienti
     */
    List<Cliente> trovaTutti();

    /**
     * Inserisce nel DB il cliente passato come parametro.
     *
     * @param cliente il cliente da salvare. Deve avere chiave primaria null
     * @return il cliente dopo il salvataggio con la chiave primaria
     * valorizzata, oppure null se non è riuscito a salvarlo
     */
    Cliente inserisci(Cliente cliente);

    /**
     * Trova nel DB il cliente con la chiave primaria fornita. Se non lo trova
     * restituisce null.
     *
     * @param id la chiave primaria del cliente da cercare. Non deve essere
     * null.
     * @return il cliente trovato oppure null se non lo trova
     */
    Cliente trovaClientePerId(Long id);

    /**
     * Trova nel DB il cliente da modificare in base alla chiave primaria del
     * cliente passato e lo modifica. Se non lo trova restituisce null.
     *
     * @param nuovo Il cliente con i dati aggiornati e la chiave primaria del
     * cliente originale
     * @return
     */
    Cliente modifica(Cliente nuovo);

    /**
     * Cerca nel DB il cliente da eliminare cercando per chiave primaria. Se lo
     * trova, lo elimina e lo ritorna, se non lo trova ritorna null
     *
     * @param id la chiave primaria da cercare. Non deve essere null.
     * @return il cliente eliminato oppure null se non l'ha trovato o non l'ha
     * potuto eliminare
     */
    Cliente elimina(Long id);

    /**
     * Restituisce tutti i clienti che hanno il criterio come chiave parziale o
     * nel nome o nel cognome o nel codice fiscale
     *
     * @param criterio la chiave parziale per la ricerca. Se la stringa è vuota
     * restituisce tutti i record. La stringa non può essere null
     * @return la lista dei clienti trovati. Se non trova nulla restituisce una
     * lista vuota.
     */
    List<Cliente> trovaPerNomeCognomeCodiceFiscale(String criterio);
}
