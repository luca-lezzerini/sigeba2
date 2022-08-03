package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.CriterioModificaTipoContoDto;
import it.iad.sigeba2.dto.CriterioCancellazioneTipoContoDto;
import it.iad.sigeba2.dto.CriterioTipoContoDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.dto.TipoContoDto;
import it.iad.sigeba2.service.TipologieContiService;
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
    public List<TipoContoDto> cercaTipoConto(@RequestBody CriterioTipoContoDto criterio) {
      return tipologieContiService.cercaTipoConto(criterio); 
    }
    
    @RequestMapping("/inserisciTipoConto")
    @ResponseBody
    public List<TipoContoDto> inserisciTipoConto(@RequestBody TipoContoDto inserisci) {
        throw new UnsupportedOperationException();
    }
    
    @RequestMapping("/cancellaTipoConto")
    @ResponseBody
    public List<TipoContoDto> cancellaTipoConto(@RequestBody CriterioCancellazioneTipoContoDto cancella) {
       throw new UnsupportedOperationException();
    }
    
    @RequestMapping("/modificaTipoConto")
    @ResponseBody
    public List<TipoContoDto> modificaTipoConto(@RequestBody CriterioModificaTipoContoDto modifica) {
        throw new UnsupportedOperationException();
    }
    
    @RequestMapping("/leggiTipoConto")
    @ResponseBody
    public List<TipoContoDto> leggiTipoConto(@RequestBody SimpleIdDto leggi) {
        throw new UnsupportedOperationException();
    }

}
