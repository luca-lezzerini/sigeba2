package it.iad.sigeba2;

import it.iad.sigeba2.dto.CriterioCancellazioneTipoContoDto;
import it.iad.sigeba2.dto.CriterioInserimentoTipoContoDto;
import it.iad.sigeba2.dto.CriterioTipoContoDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.dto.TipoContoDto;
import it.iad.sigeba2.model.TipoConto;
import it.iad.sigeba2.repository.TipoContoRepository;
import it.iad.sigeba2.service.TipologieContiService;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TipologieContiTest {

    Random random = new Random();

    @Autowired
    TipologieContiService tipologieContiService;

    @Autowired
    TipoContoRepository tipoContoRepository;

    @Test
    public void testaCaricamentoTipologieConti() {
        cancellaDatiEsistenti();

        int numeroTipiInseriti = creaTipiContoDiTest();

        verificaNumeroTipiInseriti(numeroTipiInseriti);

        TipoConto tipoScelto = scegliTipoContoACaso();

        leggeEVerificaLetturaTipoConto(tipoScelto);

        verificaRicercaPerChiaveParziale(numeroTipiInseriti);

        verificaCancellazione(tipoScelto);
    }

    private void verificaCancellazione(TipoConto tipoScelto) {
        //verifico cancellaTipoConto
        CriterioCancellazioneTipoContoDto criterioCancellazione = new CriterioCancellazioneTipoContoDto();
        criterioCancellazione.setIdTipoConto(tipoScelto.getId());
        tipologieContiService.cancellaTipoConto(criterioCancellazione);
        TipoConto tc = tipoContoRepository.findById(tipoScelto.getId())
                .map(tx -> tx)
                .orElse(null);
        Assertions.assertTrue(tc == null, "Trovato record cancellato!!!");
    }

    private void verificaRicercaPerChiaveParziale(int numeroTipiInseriti) {
        //verifico ricerca tipoconto
        CriterioTipoContoDto criterio = new CriterioTipoContoDto();
        criterio.setCriterio("Pall");
        List<TipoContoDto> contiTrovati = tipologieContiService.cercaTipoConto(criterio);
        Assertions.assertTrue(contiTrovati.size() == 2);
        for (TipoContoDto tc : contiTrovati) {
            Assertions.assertTrue(tc.getNome().contains("Pall"));
        }

        criterio.setCriterio("p");
        contiTrovati = tipologieContiService.cercaTipoConto(criterio);
        Assertions.assertTrue(contiTrovati.size() == numeroTipiInseriti);
    }

    private void leggeEVerificaLetturaTipoConto(TipoConto tipoScelto) {
        //verifico che il tipoconto con Id uguale a quello scelto abbia la descrizione "conto prova"
        SimpleIdDto sid = new SimpleIdDto();
        sid.setId(tipoScelto.getId());
        TipoConto tip = tipologieContiService.leggiTipoConto(sid);
        Assertions.assertTrue(tip != null);
        Assertions.assertTrue(tip.getDescrizione()
                .equals(tipoScelto.getDescrizione())
                && tip.getCartaCredito()
                        .equals(tipoScelto.getCartaCredito()));
    }

    private TipoConto scegliTipoContoACaso() {
        // leggo tutti i tipi conto e ne scelgo uno a caso dalla lista appena letta
        List<TipoConto> tipiConto = tipoContoRepository.findAll();
        int numTipi = tipiConto.size();
        int numContoPrescelto = random.nextInt(numTipi);
        TipoConto tipoScelto = tipiConto.get(numContoPrescelto);
        return tipoScelto;
    }

    private void verificaNumeroTipiInseriti(int numeroTipiInseriti) {
        //verifico che i tipi di conto siano 2
        CriterioTipoContoDto criterioTuttiTipoConto = new CriterioTipoContoDto("");
        List<TipoContoDto> lista = tipologieContiService.cercaTipoConto(criterioTuttiTipoConto);
        Assertions.assertTrue(lista.size() == numeroTipiInseriti);
    }

    private int creaTipiContoDiTest() {
        //creo i conti di test
        int numeroTipiInseriti = 0;
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
        numeroTipiInseriti++;
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
        numeroTipiInseriti++;
        tipoContoDto = new TipoContoDto();
        tipoContoDto.setId(null);
        tipoContoDto.setNome("Giovani");
        tipoContoDto.setDescrizione("Conto Prova 3");
        tipoContoDto.setCostoOperazione(40.20);
        tipoContoDto.setNumeroOperazioniGratis(4);
        tipoContoDto.setInteressiAnnui(1.45);
        tipoContoDto.setFido(3.66);
        tipoContoDto.setCartaCredito(false);
        tipoContoDto.setCostoOperazioneBancomat(2.5);
        criterioinserimento = new CriterioInserimentoTipoContoDto(tipoContoDto);
        tipologieContiService.inserisciTipoConto(criterioinserimento);
        numeroTipiInseriti++;
        tipoContoDto = new TipoContoDto();
        tipoContoDto.setId(null);
        tipoContoDto.setNome("Giovani2");
        tipoContoDto.setDescrizione("Conto Prova 4");
        tipoContoDto.setCostoOperazione(40.20);
        tipoContoDto.setNumeroOperazioniGratis(4);
        tipoContoDto.setInteressiAnnui(1.45);
        tipoContoDto.setFido(3.66);
        tipoContoDto.setCartaCredito(false);
        tipoContoDto.setCostoOperazioneBancomat(2.5);
        criterioinserimento = new CriterioInserimentoTipoContoDto(tipoContoDto);
        tipologieContiService.inserisciTipoConto(criterioinserimento);
        numeroTipiInseriti++;
        return numeroTipiInseriti;
    }

    private void cancellaDatiEsistenti() {
        // cancello tutti i conti
        tipoContoRepository.deleteAll();
    }
}
