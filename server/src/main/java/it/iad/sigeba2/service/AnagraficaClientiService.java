package it.iad.sigeba2.service;

import it.iad.sigeba2.dto.ClienteDto;
import it.iad.sigeba2.dto.CriterioCancellazioneClienteDto;
import it.iad.sigeba2.dto.CriterioClienteDto;
import it.iad.sigeba2.dto.CriterioModificaClienteDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.model.Cliente;
import java.util.List;

public interface AnagraficaClientiService {

    /**
     * Cerca i clienti che soddisfano il criterio di ricerca passato.
     *
     * @param criterio DTO con il criterio stringa, chiave parziale in or per
     * nome, cognome e codice fiscale
     * @return la lista dei clienti che soddisfano il criterio, se non ne trova
     * ritorna una lista vuota
     */
    List<ClienteDto> cercaCliente(CriterioClienteDto criterio);

    /**
     * Inserisce un nuovo dto nel DB
     *
     * @param dto il DTO con i dati del dto da inserire (id deve essere null o
     * zero) e il criterio di ricerca da applicare dopo l'inserimento
     * @return la lista dei clienti, dopo l'inserimento, che soddisfano il
     * criterio passato.
     */
    List<ClienteDto> inserisciCliente(ClienteDto dto);

    /**
     * Recupera i dati di un cliente fornito l'ID. Se non lo trova ritorna null.
     *
     * @param cliente il DTO con l'ID del cliente da cercare (diverso da null e
     * da 0)
     * @return il cliente trovato o null se non lo trova
     */
    Cliente leggiCliente(SimpleIdDto cliente);

    /**
     * Il metodo modifica il cliente sostituendolo con quello passato cercandolo
     * tramite id
     *
     * @param modificaDto contienem il cliente da sostituire sul data base, ed
     * il criterio per ritornare la lista dei clienti aggiornata.
     * @return la lista dei clienti aggiornata filtrata con il criterio passato
     */
    List<ClienteDto> modificaCliente(CriterioModificaClienteDto modificaDto);

    /**
     * Cancella il cliente dal DB. Il cliente da cancellare viene identificato
     * mediante
     *
     * @param dtoCancellazione
     * @return
     */
    List<ClienteDto> cancellaCliente(CriterioCancellazioneClienteDto dtoCancellazione);

    List<ClienteDto> mostraTuttiClienti();
}
