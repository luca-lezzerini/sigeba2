package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.ContoCorrenteDto;
import it.iad.sigeba2.dto.CriterioCancellazioneContoCorrenteDto;
import it.iad.sigeba2.dto.CriterioContoCorrenteDto;
import it.iad.sigeba2.dto.CriterioInserimentoContoCorrenteDto;
import it.iad.sigeba2.dto.CriterioModificaContoCorrenteDto;
import it.iad.sigeba2.dto.RispostaConStato;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.helper.SigebaStateCollector;
import it.iad.sigeba2.model.ContoCorrente;
import it.iad.sigeba2.service.ContoCorrenteService;
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
public class ContoCorrenteController {

    @Autowired
    private ContoCorrenteService contoCorrenteService;

    @RequestMapping("/cercaContoCorrente")
    @ResponseBody
    public RispostaConStato<List<ContoCorrenteDto>> cercaContoCorrente(@RequestBody CriterioContoCorrenteDto criterio) {
        List<ContoCorrenteDto> datiRisposta;
        try {
            datiRisposta = contoCorrenteService.cercaContoCorrente(criterio);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller cerca ContoCorrente");
            datiRisposta = Collections.emptyList();
        }

        RispostaConStato<List<ContoCorrenteDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    @RequestMapping("/inserisciContoCorrente")
    @ResponseBody
    public RispostaConStato<List<ContoCorrenteDto>> inserisciContoCorrente(@RequestBody CriterioInserimentoContoCorrenteDto contoCorrenteDaInserireDto) {
        List<ContoCorrenteDto> datiRisposta;
        try {
            datiRisposta = contoCorrenteService.inserisciContoCorrente(contoCorrenteDaInserireDto);
        } catch (SigebaException e) {
            log.warn("Eccezione nelcontroller cercaContoCorrente");
            datiRisposta = Collections.emptyList();
        }

        RispostaConStato<List<ContoCorrenteDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;

    }

    @RequestMapping("/modificaContoCorrente")
    @ResponseBody
    public RispostaConStato<List<ContoCorrenteDto>> modificaContoCorrente(@RequestBody CriterioModificaContoCorrenteDto modifica) {
        List<ContoCorrenteDto> datiRisposta;
        try {
            datiRisposta = contoCorrenteService.modificaContoCorrente(modifica);
        } catch (SigebaException e) {

            log.warn("Eccezione nelcontroller modificaContoCorrente");
            datiRisposta = Collections.emptyList();
        }

        RispostaConStato<List<ContoCorrenteDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    @RequestMapping("/cancellaContoCorrente")
    @ResponseBody
    public RispostaConStato<List<ContoCorrenteDto>> cancellaContoCorrente(@RequestBody CriterioCancellazioneContoCorrenteDto dtoCancellazione) {
        List<ContoCorrenteDto> datiRisposta;
        try {
            datiRisposta = contoCorrenteService.cancellaContoCorrente(dtoCancellazione);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller cancellaContoCorrente");
            datiRisposta = Collections.emptyList();
        }
        RispostaConStato<List<ContoCorrenteDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    @RequestMapping("/leggiContoCorrente")
    @ResponseBody
    public RispostaConStato<ContoCorrenteDto> leggiContoCorrente(@RequestBody SimpleIdDto idCCDaTrovare) {
        ContoCorrenteDto datiRisposta;
        try {
            ContoCorrente ccTrovato = contoCorrenteService.leggiContoCorrente(idCCDaTrovare);
            if (ccTrovato == null) {
                datiRisposta = null;
            } else {
                datiRisposta = new ContoCorrenteDto(ccTrovato);
            }
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller cancellaContoCorrente");
            datiRisposta = null;
        }
        RispostaConStato<ContoCorrenteDto> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

}
