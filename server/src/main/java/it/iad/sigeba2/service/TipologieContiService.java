package it.iad.sigeba2.service;

import it.iad.sigeba2.dto.CriterioCancellazioneTipoContoDto;
import it.iad.sigeba2.dto.CriterioInserimentoTipoContoDto;
import it.iad.sigeba2.dto.CriterioModificaTipoContoDto;
import it.iad.sigeba2.dto.CriterioTipoContoDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.dto.TipoContoDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.model.TipoConto;
import java.util.List;

public interface TipologieContiService {

    /**
     * Cerca la tipologia di conto che soddisfa il criterio di ricerca passato.
     *
     * @param criterio DTO con il criterio stringa, chiave parziale in or per
     * nome o descrizione
     * @return la lista dei tipi di conto che soddisfano il criterio, se non ne
     * trova ritorna una lista vuota
     * @throws it.iad.sigeba2.exception.SigebaException
     */
    List<TipoContoDto> cercaTipoConto(CriterioTipoContoDto criterio) throws SigebaException;

    /**
     * inserisce un nuovo DTO nel DB
     *
     * @param contoDaInserireDto il DTO con i dati del dto da inserire e il
     * criterio di ricerca da applicare dopo l'inserimento
     * @return la lista dei tipi di conto, dopo l'inserimento, che soddisfano il
     * criterio passato.
     * @throws it.iad.sigeba2.exception.SigebaException
     */
    List<TipoContoDto> inserisciTipoConto(CriterioInserimentoTipoContoDto contoDaInserireDto) throws SigebaException;

    /**
     * Cancella il tipo di conto dal DB.Il cliente da cancellare viene
     * identificato mediante il criterio
     *
     * @param dtoCancellazione
     * @return ritorna i clienti rimasti
     * @throws it.iad.sigeba2.exception.SigebaException
     */
    List<TipoContoDto> cancellaTipoConto(CriterioCancellazioneTipoContoDto dtoCancellazione) throws SigebaException;

    /**
     * Modifica il tipo di conto sostituendolo con quello passato tramite nome o
     * descrizione
     *
     * @param modifica contiene il cliente da sostituire sul DB, e il criterio
     * per ritornare la lista dei tipi di conto aggiornata
     * @return la lista dei tipi di conto aggiornata filtrata con il criterio
     * passato
     * @throws it.iad.sigeba2.exception.SigebaException
     */
    List<TipoContoDto> modificaTipoConto(CriterioModificaTipoContoDto modifica) throws SigebaException;

    /**
     * Recupera i dati di un tipo di conto fornito il nome o la descrizione.Se
     * non lo trova ritorna null.
     *
     * @param tipocontodto il DTO con il nome o la descrizione del tipo di conto
     * da cercare (diverso da null e da 0)
     * @return il tipo di conto trovato o null se non lo trova
     * @throws it.iad.sigeba2.exception.SigebaException
     */
    TipoConto leggiTipoConto(SimpleIdDto tipocontodto) throws SigebaException;

}
