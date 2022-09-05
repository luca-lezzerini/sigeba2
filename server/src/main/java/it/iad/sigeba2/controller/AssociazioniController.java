package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.ClienteDto;
import it.iad.sigeba2.dto.CriterioCancellazioneClienteDto;
import it.iad.sigeba2.dto.CriterioInserimentoClienteDto;
import it.iad.sigeba2.dto.CriterioModificaClienteDto;
import it.iad.sigeba2.dto.IdAssociazioneDto;
import it.iad.sigeba2.dto.RispostaConStato;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.helper.SigebaStateCollector;
import it.iad.sigeba2.model.Cliente;
import it.iad.sigeba2.service.AssociazioniService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@Slf4j
@RestController
public class AssociazioniController {

    @Autowired
    private AssociazioniService associazioniService;

    @RequestMapping("/associaClienteAConto")
    @ResponseBody
    public RispostaConStato<Void> associaClienteAConto(@RequestBody IdAssociazioneDto idAssociazione) {
        log.debug("Entrato nel controller associaClienteAConto");
        try {
            associazioniService.associaClienteAConto(idAssociazione.getIdConto(), idAssociazione.getIdCliente());
        } catch (SigebaException e) {
            log.warn("Ricevuta eccezione dal servizio associaClienteAConto", e);
        }
        RispostaConStato<Void> risposta = new RispostaConStato<>();
        risposta.setStato(SigebaStateCollector.getAndClean());
        return risposta;
    }
}
