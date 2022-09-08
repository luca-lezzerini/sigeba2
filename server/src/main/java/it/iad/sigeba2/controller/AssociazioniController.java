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
        log.info("Entrato nel controller associaClienteAConto");
        try {
            associazioniService.associaClienteAConto(idAssociazione.getIdConto(), idAssociazione.getIdCliente());
        } catch (SigebaException e) {
            log.warn("Ricevuta eccezione dal servizio associaClienteAConto", e);
        }
        RispostaConStato<Void> risposta = new RispostaConStato<>();
        risposta.setStato(SigebaStateCollector.getAndClean());
        return risposta;
    }
    
    @RequestMapping("/disassociaClienteDaConto")
    @ResponseBody
    public RispostaConStato<Void> disassociaClienteDaConto(@RequestBody IdAssociazioneDto idAssociazione) {
        log.info("Entrato nel controller disassociaClienteDaConto");
        try {
            associazioniService.disassociaClienteDaConto(idAssociazione.getIdConto(), idAssociazione.getIdCliente());
        } catch (SigebaException e) {
            log.warn("Ricevuta eccezione dal servizio disassociaClienteDaConto", e);
        }
        RispostaConStato<Void> risposta = new RispostaConStato<>();
        risposta.setStato(SigebaStateCollector.getAndClean());
        return risposta;
        
    }
    
@RequestMapping("/associaContoACliente")
    @ResponseBody
    public RispostaConStato<Void> associaContoACliente(@RequestBody IdAssociazioneDto idAssociazione) {
        log.info("Entrato nel controller associaContoACliente");
        try {
            associazioniService.associaContoACliente(idAssociazione.getIdCliente(), idAssociazione.getIdConto());
        } catch (SigebaException e) {
            log.warn("Ricevuta eccezione dal servizio associaContoACliente", e);
        }
        RispostaConStato<Void> risposta = new RispostaConStato<>();
        risposta.setStato(SigebaStateCollector.getAndClean());
        return risposta;
    }

@RequestMapping("/disassociaContoDaCliente")
    @ResponseBody
    public RispostaConStato<Void> disassociaContoDaCliente(@RequestBody IdAssociazioneDto idAssociazione) {
        log.info("Entrato nel controller disassociaContoDaCliente");
        try {
            associazioniService.disassociaContoDaCliente(idAssociazione.getIdCliente(), idAssociazione.getIdConto());
        } catch (SigebaException e) {
            log.warn("Ricevuta eccezione dal servizio disassociaContoDaCliente", e);
        }
        RispostaConStato<Void> risposta = new RispostaConStato<>();
        risposta.setStato(SigebaStateCollector.getAndClean());
        return risposta;
        
    }

  @RequestMapping("/associaTipoContoAConto")
    @ResponseBody
    public RispostaConStato<Void> associaTipoContoAConto(@RequestBody IdAssociazioneDto idAssociazione) {
        log.info("Entrato nel controller associaTipoContoAConto");
        try {
            associazioniService.associaTipoContoAConto(idAssociazione.getIdConto(), idAssociazione.getIdTipoConto());
        } catch (SigebaException e) {
            log.warn("Ricevuta eccezione dal servizio associaTipoContoAConto", e);
        }
        RispostaConStato<Void> risposta = new RispostaConStato<>();
        risposta.setStato(SigebaStateCollector.getAndClean());
        return risposta;
    }
    
    @RequestMapping("/disassociaTipoContoDaConto")
    @ResponseBody
    public RispostaConStato<Void> disassociaTipoContoDaConto(@RequestBody IdAssociazioneDto idAssociazione) {
        log.info("Entrato nel controller disassociaTipoContoDaConto");
        try {
            associazioniService.disassociaTipoContoDaConto(idAssociazione.getIdConto(), idAssociazione.getIdTipoConto());
        } catch (SigebaException e) {
            log.warn("Ricevuta eccezione dal servizio disassociaTipoContoDaConto", e);
        }
        RispostaConStato<Void> risposta = new RispostaConStato<>();
        risposta.setStato(SigebaStateCollector.getAndClean());
        return risposta;
        
    }


}
