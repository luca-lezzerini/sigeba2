package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.CriterioCancellazioneLibrettoRisparmioDto;
import it.iad.sigeba2.dto.CriterioInserimentoLibrettoRisparmioDto;
import it.iad.sigeba2.dto.CriterioLibrettoRisparmioDto;
import it.iad.sigeba2.dto.CriterioModificaLibrettoRisparmioDto;
import it.iad.sigeba2.dto.LibrettoRisparmioDto;
import it.iad.sigeba2.dto.RispostaConStato;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.helper.SigebaStateCollector;
import it.iad.sigeba2.model.LibrettoRisparmio;
import it.iad.sigeba2.service.LibrettoRisparmioService;
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
public class LibrettoRisparmioController {
    
    @Autowired
    private LibrettoRisparmioService librettoRisparmioService;
    
    @RequestMapping("/cercaLibrettoRisparmio")
    @ResponseBody
    public RispostaConStato<List<LibrettoRisparmioDto>> cercaLibrettoRisparmio(@RequestBody CriterioLibrettoRisparmioDto criterio) {
        List<LibrettoRisparmioDto> datiRisposta;
        try {
            datiRisposta = librettoRisparmioService.cercaLibrettoRisparmio(criterio);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller cercaLibrettoRisparmio");
            datiRisposta = Collections.emptyList();
        }

        RispostaConStato<List<LibrettoRisparmioDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    @RequestMapping("/inserisciLibrettoRisparmio")
    @ResponseBody
    public RispostaConStato<List<LibrettoRisparmioDto>> inserisciLibrettoRisparmio(@RequestBody CriterioInserimentoLibrettoRisparmioDto librettoRisparmioDaInserireDto) {
        List<LibrettoRisparmioDto> datiRisposta;
        try {
            datiRisposta = librettoRisparmioService.inserisciLibrettoRisparmio(librettoRisparmioDaInserireDto);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller inserisciLibrettoRisparmio");
            datiRisposta = Collections.emptyList();
        }
        RispostaConStato<List<LibrettoRisparmioDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    @RequestMapping("/cancellaLibrettoRisparmio")
    @ResponseBody
    public RispostaConStato<List<LibrettoRisparmioDto>> cancellaLibrettoRisparmio(@RequestBody CriterioCancellazioneLibrettoRisparmioDto dtoCancellazione) {
        List<LibrettoRisparmioDto> datiRisposta;
        try {
            datiRisposta = librettoRisparmioService.cancellaLibrettoRisparmio(dtoCancellazione);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller cancellaLibrettoRisparmio");
            datiRisposta = Collections.emptyList();
        }
        RispostaConStato<List<LibrettoRisparmioDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    @RequestMapping("/modificaLibrettoRisparmio")
    @ResponseBody
    public RispostaConStato<List<LibrettoRisparmioDto>> modificaLibrettoRisparmio(@RequestBody CriterioModificaLibrettoRisparmioDto modifica) {
        List<LibrettoRisparmioDto> datiRisposta;
        try {
            datiRisposta = librettoRisparmioService.modificaLibrettoRisparmio(modifica);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller modificaLibrettoRisparmio");
            datiRisposta = Collections.emptyList();
        }
        RispostaConStato<List<LibrettoRisparmioDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    @RequestMapping("/leggiLibrettoRisparmio")
    @ResponseBody
    public LibrettoRisparmio leggiLibrettoRisparmio(@RequestBody SimpleIdDto librettoRisparmioDto) throws SigebaException {
        return librettoRisparmioService.leggiLibrettoRisparmio(librettoRisparmioDto);
    }
}
