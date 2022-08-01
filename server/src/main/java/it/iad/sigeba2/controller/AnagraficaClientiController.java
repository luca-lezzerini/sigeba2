package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.CriterioClienteDto;
import it.iad.sigeba2.dto.ClienteDto;
import it.iad.sigeba2.dto.CriterioModificaClienteDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.model.Cliente;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnagraficaClientiController {

    /* Il metodo prende in input un criterio di ricerca inserito dall'utente, tipicamente una stringa, 
    che potrebbe essere una parte del nome o del cognome o del codice fiscale del cliente da cercare. 
    Una volta inserito questo criterio di ricerca, comparirà l'elenco dei clienti che soddisfano 
    quella ricerca, sotto forma di tabella. */
    @RequestMapping("/cercaCliente")
    @ResponseBody
    public List<ClienteDto> cercaCliente(@RequestBody CriterioClienteDto criterio) {
        throw new UnsupportedOperationException();
    }

    @ResponseBody
    @RequestMapping("/inserisciCliente")
    public List<ClienteDto> inserisciCliente(@RequestBody ClienteDto cliente) {
        throw new UnsupportedOperationException();
    }

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
