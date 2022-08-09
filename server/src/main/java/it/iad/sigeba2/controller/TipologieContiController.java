package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.CriterioModificaTipoContoDto;
import it.iad.sigeba2.dto.CriterioCancellazioneTipoContoDto;
import it.iad.sigeba2.dto.CriterioInserimentoTipoContoDto;
import it.iad.sigeba2.dto.CriterioTipoContoDto;
import it.iad.sigeba2.dto.RispostaConStato;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.dto.TipoContoDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.helper.SigebaStateCollector;
import it.iad.sigeba2.service.TipologieContiService;
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
public class TipologieContiController {

    @Autowired
    private TipologieContiService tipologieContiService;

    @RequestMapping("/cercaTipoConto")
    @ResponseBody
    public RispostaConStato<List<TipoContoDto>> cercaTipoConto(@RequestBody CriterioTipoContoDto criterio) {
        List<TipoContoDto> datiRisposta;
        try {
            datiRisposta = tipologieContiService.cercaTipoConto(criterio);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller cercaTipoConto");
            datiRisposta = Collections.emptyList();
        }

        RispostaConStato<List<TipoContoDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    @RequestMapping("/inserisciTipoConto")
    @ResponseBody
    public RispostaConStato<List<TipoContoDto>> inserisciTipoConto(@RequestBody CriterioInserimentoTipoContoDto contoDaInserireDto) {
        List<TipoContoDto> datiRisposta;
        try {
            datiRisposta = tipologieContiService.inserisciTipoConto(contoDaInserireDto);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller inserisciTipoConto");
            datiRisposta = Collections.emptyList();
        }
        RispostaConStato<List<TipoContoDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    @RequestMapping("/cancellaTipoConto")
    @ResponseBody
    public List<TipoContoDto> cancellaTipoConto(@RequestBody CriterioCancellazioneTipoContoDto dtoCancellazione) {
        try {
            return tipologieContiService.cancellaTipoConto(dtoCancellazione);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller cancellaTipoConto");
            return Collections.emptyList();
        }
    }

    @RequestMapping("/modificaTipoConto")
    @ResponseBody
    public List<TipoContoDto> modificaTipoConto(@RequestBody CriterioModificaTipoContoDto modifica) {
       return tipologieContiService.modificaTipoConto(modifica);
    }

    @RequestMapping("/leggiTipoConto")
    @ResponseBody
    public List<TipoContoDto> leggiTipoConto(@RequestBody SimpleIdDto leggi) {
        throw new UnsupportedOperationException();
    }

}
