package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.CriterioClienteDto;
import it.iad.sigeba2.dto.ClienteDto;
import it.iad.sigeba2.dto.CriterioModificaClienteDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.model.Cliente;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class AnagraficaClientiController {

    /* Il metodo prende in input un criterio di ricerca inserito dall'utente, tipicamente una stringa, 
    che potrebbe essere una parte del nome o del cognome o del codice fiscale del cliente da cercare. 
    Una volta inserito questo criterio di ricerca, comparirà l'elenco dei clienti che soddisfano 
    quella ricerca, sotto forma di tabella. */
    /**
     * Cerca i clienti che soddisfano il criterio di ricerca passato.
     *
     * @param criterio DTO con il criterio stringa, chiave parziale in or per
     * nome, cognome e codice fiscale
     * @return la lista dei clienti che soddisfano il criterio, se non ne trova
     * ritorna una lista vuota
     */
    @RequestMapping("/cercaCliente")
    @ResponseBody
    public List<ClienteDto> cercaCliente(@RequestBody CriterioClienteDto criterio) {
        log.debug("Entrato");
        throw new UnsupportedOperationException();
    }

    /**
     * Inserisce un nuovo cliente nel DB
     *
     * @param cliente il DTO con i dati del cliente da inserire (id deve essere
     * null o zero) e il criterio di ricerca da applicare dopo l'inserimento
     * @return la lista dei clienti, dopo l'inserimento, che soddisfano ilc
     * riterio passato.
     */
    @ResponseBody
    @RequestMapping("/inserisciCliente")
    public List<ClienteDto> inserisciCliente(@RequestBody ClienteDto cliente) {
        throw new UnsupportedOperationException();
    }

    /**
     * Recupera i dati di un cliente fornito l'ID. Se non lo trova ritorna null.
     * @param cliente il DTO con l'ID del cliente da cercare (diverso da null e da 0)
     * @return il cliente trovato o null se non lo trova
     */
    @ResponseBody
    @RequestMapping("/leggiCliente")
    public Cliente leggiCliente(@RequestBody SimpleIdDto cliente) {
        throw new UnsupportedOperationException();

    }

    @ResponseBody
    @RequestMapping("/modificaCliente")
    public List<ClienteDto> modificaCliente(@RequestBody CriterioModificaClienteDto modifica) {
        throw new UnsupportedOperationException();

    }

    @RequestMapping("/cancellaCliente")
    @ResponseBody
    public List<ClienteDto> cancellaCliente(@RequestBody CriterioClienteDto criterio) {
        throw new UnsupportedOperationException();
    }
}
