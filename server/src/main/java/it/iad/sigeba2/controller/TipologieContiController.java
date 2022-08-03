package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.CriterioModificaTipoContoDto;
import it.iad.sigeba2.dto.CriterioCancellazioneTipoContoDto;
import it.iad.sigeba2.dto.CriterioTipoContoDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.dto.TipoContoDto;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TipologieContiController {

    @RequestMapping("/cercaTipoConto")
    @ResponseBody
    public List<TipoContoDto> cercaTipoContoDto(@RequestBody CriterioTipoContoDto criterio) {
        throw new UnsupportedOperationException();
    }
    
    @RequestMapping("/inserisciTipoConto")
    @ResponseBody
    public List<TipoContoDto> inserisciTipoContoDto(@RequestBody TipoContoDto inserisci) {
        throw new UnsupportedOperationException();
    }
    
    @RequestMapping("/cancellaTipoConto")
    @ResponseBody
    public List<TipoContoDto> cancellaTipoContoDto(@RequestBody CriterioCancellazioneTipoContoDto cancella) {
        throw new UnsupportedOperationException();
    }
    
    @RequestMapping("/modificaTipoConto")
    @ResponseBody
    public List<TipoContoDto> modificaTipoContoDto(@RequestBody CriterioModificaTipoContoDto modifica) {
        throw new UnsupportedOperationException();
    }
    
    @RequestMapping("/leggiTipoConto")
    @ResponseBody
    public List<TipoContoDto> leggiTipoContoDto(@RequestBody SimpleIdDto leggi) {
        throw new UnsupportedOperationException();
    }

}
