package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.CriterioModificaFilialeDto;
import it.iad.sigeba2.dto.CriterioCancellazioneFilialeDto;
import it.iad.sigeba2.dto.CriterioInserimentoFilialeDto;
import it.iad.sigeba2.dto.CriterioFilialeDto;
import it.iad.sigeba2.dto.RispostaConStato;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.dto.FilialeDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.helper.SigebaStateCollector;
import it.iad.sigeba2.service.FilialeService;
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
public class FilialeController {

    @Autowired
    private FilialeService filialeService;

    @RequestMapping("/cercaFiliale")
    @ResponseBody
    public RispostaConStato<List<FilialeDto>> cercaFiliale(@RequestBody CriterioFilialeDto criterio) {
        List<FilialeDto> datiRisposta;
        try {
            datiRisposta = filialeService.cercaFiliale(criterio);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller cercaFiliale");
            datiRisposta = Collections.emptyList();
        }

        RispostaConStato<List<FilialeDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    @RequestMapping("/inserisciFiliale")
    @ResponseBody
    public RispostaConStato<List<FilialeDto>> inserisciTipoConto(@RequestBody CriterioInserimentoFilialeDto filialeDaInserireDto) {
        List<FilialeDto> datiRisposta;
        try {
            datiRisposta = filialeService.inserisciFiliale(filialeDaInserireDto);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller inserisciFiliale");
            datiRisposta = Collections.emptyList();
        }
        RispostaConStato<List<FilialeDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    @RequestMapping("/cancellaFiliale")
    @ResponseBody
    public RispostaConStato<List<FilialeDto>> cancellaFiliale(@RequestBody CriterioCancellazioneFilialeDto dtoCancellazione) {
        List<FilialeDto> datiRisposta;
        try {
            datiRisposta = filialeService.cancellaFiliale(dtoCancellazione);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller cancellaFiliale");
            datiRisposta = Collections.emptyList();
        }
        RispostaConStato<List<FilialeDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    @RequestMapping("/modificaFiliale")
    @ResponseBody
    public RispostaConStato<List<FilialeDto>> modificaFiliale(@RequestBody CriterioModificaFilialeDto modifica) {
        List<FilialeDto> datiRisposta;
        try {
            datiRisposta = filialeService.modificaFiliale(modifica);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller modificaFiliale");
            datiRisposta = Collections.emptyList();
        }
        RispostaConStato<List<FilialeDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    @RequestMapping("/leggiFiliale")
    @ResponseBody
    public List<FilialeDto> leggiFiliale(@RequestBody SimpleIdDto leggi) {
        throw new UnsupportedOperationException();
    }

}
