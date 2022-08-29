package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.CriterioCancellazioneImpiegatoDto;
import it.iad.sigeba2.dto.CriterioImpiegatoDto;
import it.iad.sigeba2.dto.CriterioInserimentoImpiegatoDto;
import it.iad.sigeba2.dto.CriterioModificaImpiegatoDto;
import it.iad.sigeba2.dto.ImpiegatoDto;
import it.iad.sigeba2.dto.RispostaConStato;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.dto.TipoContoDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.helper.SigebaStateCollector;
import it.iad.sigeba2.service.ImpiegatoService;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class ImpiegatoController {

    @Autowired
    private ImpiegatoService impiegatoService;

    @RequestMapping("/cercaImpiegato")
    @ResponseBody
    public RispostaConStato<List<ImpiegatoDto>> cercaImpiegato(@RequestBody CriterioImpiegatoDto criterio) {
        List<ImpiegatoDto> datiRisposta;
        try {
            datiRisposta = impiegatoService.cercaImpiegato(criterio);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller cercaImpiegato");
            datiRisposta = Collections.emptyList();
        }

        RispostaConStato<List<ImpiegatoDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    @RequestMapping("/inserisciImpiegato")
    @ResponseBody
    public RispostaConStato<List<ImpiegatoDto>> inserisciImpiegato(@RequestBody CriterioInserimentoImpiegatoDto impiegatoDaInserireDto) {
        List<ImpiegatoDto> datiRisposta;
        try {
            datiRisposta = impiegatoService.inserisciImpiegato(impiegatoDaInserireDto);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller inserisciImpiegato");
            datiRisposta = Collections.emptyList();
        }
        RispostaConStato<List<ImpiegatoDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }
    
    @RequestMapping("/cancellaImpiegato")
    @ResponseBody
    public RispostaConStato<List<ImpiegatoDto>> cancellaImpiegato(@RequestBody CriterioCancellazioneImpiegatoDto dtoCancellazione) {
        List<ImpiegatoDto> datiRisposta;
        try {
            datiRisposta = impiegatoService.cancellaImpiegato(dtoCancellazione);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller cancellaImpiegato");
            datiRisposta = Collections.emptyList();
        }
        RispostaConStato<List<ImpiegatoDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    @RequestMapping("/modificaImpiegato")
    @ResponseBody
    public RispostaConStato<List<ImpiegatoDto>> modificaImpiegato(@RequestBody CriterioModificaImpiegatoDto modifica) {
        List<ImpiegatoDto> datiRisposta;
        try {
            datiRisposta = impiegatoService.modificaImpiegato(modifica);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller modificaImpiegato");
            datiRisposta = Collections.emptyList();
        }
        RispostaConStato<List<ImpiegatoDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }
        
    @RequestMapping("/leggiImpiegato")
    @ResponseBody
    public List<TipoContoDto> leggiTipoConto(@RequestBody SimpleIdDto leggi) {
        throw new UnsupportedOperationException();
    }














}
