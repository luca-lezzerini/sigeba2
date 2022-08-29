package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.CriterioCancellazioneMovimentoCCDto;
import it.iad.sigeba2.dto.CriterioInserimentoMovimentoCCDto;
import it.iad.sigeba2.dto.CriterioModificaMovimentoCCDto;
import it.iad.sigeba2.dto.CriterioMovimentoCCDto;
import it.iad.sigeba2.dto.MovimentoCCDto;
import it.iad.sigeba2.dto.RispostaConStato;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.helper.SigebaStateCollector;
import it.iad.sigeba2.service.MovimentoCCService;
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
public class MovimentoCCController {
    
    @Autowired
    private MovimentoCCService movimentoccService;
    
    @RequestMapping("/cercaMovimentoCC")
    @ResponseBody
    public RispostaConStato<List<MovimentoCCDto>>cercaMovimentoCC(@RequestBody CriterioMovimentoCCDto criterio){
        List<MovimentoCCDto>datiRisposta;
        try{
        datiRisposta = movimentoccService.cercaMovimentoCC(criterio);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller cercaMovimentoCC");
            datiRisposta = Collections.emptyList();
        }
      RispostaConStato<List<MovimentoCCDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;   
        
    }
    
@RequestMapping("/inserisciMovimentoCC")
@ResponseBody
public RispostaConStato<List<MovimentoCCDto>>inserisciTipoConto(@RequestBody CriterioInserimentoMovimentoCCDto movimentoccDaInserireDto){
    List<MovimentoCCDto> datiRisposta;
    try{
        datiRisposta = movimentoccService.inserisciMovimentoCC(movimentoccDaInserireDto);
    } catch(SigebaException e) {
        log.warn("Eccezione nel controller inserisciMovimentoCC");
        datiRisposta = Collections.emptyList();
    }
RispostaConStato<List<MovimentoCCDto>> risp = new RispostaConStato<>(
        datiRisposta,
        SigebaStateCollector.getAndClean());
     return risp;
    }

@RequestMapping("/cancellaMovimentoCC")
@ResponseBody
    public RispostaConStato<List<MovimentoCCDto>> cancellaMovimentoCC(@RequestBody CriterioCancellazioneMovimentoCCDto dtoCancellazione) {
        List<MovimentoCCDto> datiRisposta;
        try {
            datiRisposta = movimentoccService.cancellaMovimentoCC(dtoCancellazione);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller cancellaMovimentoCC");
            datiRisposta = Collections.emptyList();
        }
        RispostaConStato<List<MovimentoCCDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
}

 @RequestMapping("/modificaMovimentoCC")
 @ResponseBody
    public RispostaConStato<List<MovimentoCCDto>> modificaFiliale(@RequestBody CriterioModificaMovimentoCCDto modifica) {
        List<MovimentoCCDto> datiRisposta;
        try {
            datiRisposta = movimentoccService.modificaMovimentoCC(modifica);
        } catch (SigebaException e) {
            log.warn("Eccezione nel controller modificaMovimentoCC");
            datiRisposta = Collections.emptyList();
        }
        RispostaConStato<List<MovimentoCCDto>> risp = new RispostaConStato<>(
                datiRisposta,
                SigebaStateCollector.getAndClean());
        return risp;
    }

    @RequestMapping("/leggiMovimentoCC")
    @ResponseBody
    public List<MovimentoCCDto> leggiMovimentoCC(@RequestBody SimpleIdDto leggi) {
        throw new UnsupportedOperationException();
    }










}
