package it.iad.sigeba2.service;

import it.iad.sigeba2.dto.CriterioCancellazioneLibrettoRisparmioDto;
import it.iad.sigeba2.dto.CriterioInserimentoLibrettoRisparmioDto;
import it.iad.sigeba2.dto.CriterioLibrettoRisparmioDto;
import it.iad.sigeba2.dto.CriterioModificaLibrettoRisparmioDto;
import it.iad.sigeba2.dto.LibrettoRisparmioDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.model.LibrettoRisparmio;
import java.util.List;

public interface LibrettoRisparmioService {
 
    /**
     * Cerca i libretti che soddisfano il criterio di ricerca passato.
     * @param criterio DTO con il criterio stringa, chiave parziale per il codice del libretto
     * @return la lista dei libretti che soddisfano il criterio di ricerca passato,
     * se non ne trova torna una lista vuota.
     * @throws SigebaException 
     */
    List<LibrettoRisparmioDto> cercaLibrettoRisparmio(CriterioLibrettoRisparmioDto criterio) throws SigebaException;
    
    /**
     * Inserisce un nuovo libretto risparmio nel DB
     * @param librettoRisparmioDaInserireDto il DTO con i dati del librettoRisparmio 
     * da inserire (id deve essere null o zero) e il criterio di ricerca da applicare dopo l'inserimento
     * @return la lista dei librettiRisparmio, dopo l'inserimento, che soddisfano il criterio passato
     * @throws SigebaException 
     */
    List<LibrettoRisparmioDto> inserisciLibrettoRisparmio(CriterioInserimentoLibrettoRisparmioDto librettoRisparmioDaInserireDto) throws SigebaException;
    
    /**
     * Cancella il librettoRisparmio dal DB. Il libretto da cancellare viene identificato mediante 
     * @param dtoCancellazione che contiene l'id del libretto da cancellare
     * @return la lista dei librettiRisparmio, dopo la rimozione.
     * @throws SigebaException 
     */
    List<LibrettoRisparmioDto> cancellaLibrettoRisparmio(CriterioCancellazioneLibrettoRisparmioDto dtoCancellazione) throws SigebaException;
    
    /**
     * Il metodo modifica il librettoRisparmio sostituendolo con quello passato cercandolo
     * tramite id
     * @param modifica contiene il libretto da sostituire sul DB, e il criterio 
     * per ritornare la lista dei librettiRisparmio aggiornata.
     * @return la lista dei libretti aggiornata e filtrata con il criterio passato
     * @throws SigebaException 
     */
    List<LibrettoRisparmioDto> modificaLibrettoRisparmio(CriterioModificaLibrettoRisparmioDto modifica) throws SigebaException;
    
    /**
     * Recupera i dati di un librettoRisparmio, fornito l'ID. Se non lo trova ritorna null.
     * @param librettoRisparmioDto il DTO con l'ID del libretto da cercare (diverso da null e da 0)
     * @return il libretto trovato o null se non lo trova
     * @throws SigebaException 
     */
    LibrettoRisparmio leggiLibrettoRisparmio(SimpleIdDto librettoRisparmioDto) throws SigebaException;
    
    
}
