package it.iad.sigeba2;

import it.iad.sigeba2.dto.CriterioInserimentoTipoContoDto;
import it.iad.sigeba2.dto.CriterioTipoContoDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.dto.TipoContoDto;
import it.iad.sigeba2.model.TipoConto;
import it.iad.sigeba2.repository.TipoContoRepository;
import it.iad.sigeba2.service.TipologieContiService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TipologieContiTest {

    @Autowired
    TipologieContiService tipologieContiService;

    @Autowired
    TipoContoRepository tipoContoRepository;

    @Test
    public void testaCaricamentoTipologieConti() {
        // cancello tutti i conti
        tipoContoRepository.deleteAll();

        //creo i conti di test
        TipoContoDto tipoContoDto = new TipoContoDto();
        tipoContoDto.setId(null);
        tipoContoDto.setNome("PincoPallo");
        tipoContoDto.setDescrizione("Conto Prova");
        tipoContoDto.setCostoOperazione(23.23);
        tipoContoDto.setNumeroOperazioniGratis(3);
        tipoContoDto.setInteressiAnnui(1.15);
        tipoContoDto.setFido(3.55);
        tipoContoDto.setCartaCredito(true);
        tipoContoDto.setCostoOperazioneBancomat(1.5);
        CriterioInserimentoTipoContoDto criterioinserimento = new CriterioInserimentoTipoContoDto(tipoContoDto);
        tipologieContiService.inserisciTipoConto(criterioinserimento);
        tipoContoDto = new TipoContoDto();
        tipoContoDto.setId(null);
        tipoContoDto.setNome("Pallalcentro");
        tipoContoDto.setDescrizione("Conto Prova 2");
        tipoContoDto.setCostoOperazione(20.20);
        tipoContoDto.setNumeroOperazioniGratis(5);
        tipoContoDto.setInteressiAnnui(2.45);
        tipoContoDto.setFido(5.66);
        tipoContoDto.setCartaCredito(false);
        tipoContoDto.setCostoOperazioneBancomat(1.5);
        criterioinserimento = new CriterioInserimentoTipoContoDto(tipoContoDto);
        tipologieContiService.inserisciTipoConto(criterioinserimento);

        //verifico che i tipi di conto siano 2
        CriterioTipoContoDto criterioTuttiTipoConto = new CriterioTipoContoDto("");
        List<TipoContoDto> lista = tipologieContiService.cercaTipoConto(criterioTuttiTipoConto);
        Assertions.assertTrue(lista.size() == 2);

        //verifico che il tipoconto con Id 7 abbia la descrizione "conto prova"
        SimpleIdDto sid = new SimpleIdDto();
        sid.setId(13L);
        TipoConto tip = tipologieContiService.leggiTipoConto(sid);
        Assertions.assertTrue(tip != null);
        Assertions.assertTrue(tip.getDescrizione().equals("Conto Prova") && tip.getCartaCredito().equals(true));

        //verifico ricerca tipoconto
        CriterioTipoContoDto criterio = new CriterioTipoContoDto();
        criterio.setCriterio("Pall");
        List<TipoContoDto> contiTrovati = tipologieContiService.cercaTipoConto(criterio);
        Assertions.assertTrue(contiTrovati.size() == 2);
        Assertions.assertTrue(contiTrovati.get(0).getNome().equals("PincoPallo"));
        criterio.setCriterio("p");
        contiTrovati = tipologieContiService.cercaTipoConto(criterio);
         Assertions.assertTrue(contiTrovati.size() == 1);
         
         
    }
}
