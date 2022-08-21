package it.iad.sigeba2;

import it.iad.sigeba2.dto.ContoCorrenteDto;
import it.iad.sigeba2.dto.CriterioCancellazioneContoCorrenteDto;
import it.iad.sigeba2.dto.CriterioContoCorrenteDto;
import it.iad.sigeba2.dto.CriterioInserimentoContoCorrenteDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.model.ContoCorrente;
import it.iad.sigeba2.repository.ContoCorrenteRepository;
import it.iad.sigeba2.service.ContoCorrenteService;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ContoCorrenteTest {

    Random random = new Random();

    @Autowired
    ContoCorrenteService contoCorrenteService;

    @Autowired
    ContoCorrenteRepository contoCorrenteRepository;

    @Test
    public void testaCaricamentoContoCorrente() {
        cancellaDatiEsistenti();

        int numeroContiCorrentiInseriti = creaContoCorrenteDiTest();

        verificaNumeroContoCorrenteInseriti(numeroContiCorrentiInseriti);

        ContoCorrente tipoScelto = scegliContoCorrenteACaso();

        leggeEVerificaLetturaContoCorrente(tipoScelto);

        verificaRicercaPerChiaveParziale(numeroContiCorrentiInseriti);

        verificaCancellazione(tipoScelto);
    }

    private void verificaCancellazione(ContoCorrente tipoScelto) {
//Verifico cancellaContoCorrente
        Assertions.assertThrows(
                SigebaException.class,
                () -> contoCorrenteService.cancellaContoCorrente(null));
        CriterioCancellazioneContoCorrenteDto criterioCancellazioneContoCorrenete = new CriterioCancellazioneContoCorrenteDto();
        criterioCancellazioneContoCorrenete.setIdContoCorrente(tipoScelto.getId());
        try {
            contoCorrenteService.cancellaContoCorrente(criterioCancellazioneContoCorrenete);
        } catch (SigebaException e) {
            Assertions.fail();
        }
        ContoCorrente cc = contoCorrenteRepository.findById(tipoScelto.getId())
                .map(tx -> tx)
                .orElse(null);
        Assertions.assertTrue(cc == null, "Trovato record cancellato");
    }

    private void verificaRicercaPerChiaveParziale(int numeroContiCorrentiInseriti) {
        //verifico ricerca conto corrente
        CriterioContoCorrenteDto criterio = new CriterioContoCorrenteDto();
        criterio.setCriterio("9k");
        try {
            List<ContoCorrenteDto> contiCorrentiTrovati = contoCorrenteService.cercaContoCorrente(criterio);
            Assertions.assertTrue(contiCorrentiTrovati.size() == 2);
            for (ContoCorrenteDto cc : contiCorrentiTrovati) {
                Assertions.assertTrue(cc.getIban().contains("9k"));
            }

            criterio.setCriterio("");
            contiCorrentiTrovati = contoCorrenteService.cercaContoCorrente(criterio);
            Assertions.assertTrue(contiCorrentiTrovati.size() == numeroContiCorrentiInseriti);
        } catch (SigebaException e) {
            Assertions.fail();
        }
    }

    private void leggeEVerificaLetturaContoCorrente(ContoCorrente tipoScelto) {
//verifico che il conto corrente con Id uguale a quello scelto abbia il fido "500.00"
        SimpleIdDto sid = new SimpleIdDto();
        sid.setId(tipoScelto.getId());
        try {
            ContoCorrente cc = contoCorrenteService.leggiContoCorrente(sid);
            Assertions.assertTrue(cc != null);
            Assertions.assertTrue(cc.getFido()
                    .equals(tipoScelto.getFido())
                    && cc.getIban()
                            .equals(tipoScelto.getIban()));
        } catch (SigebaException e) {
            Assertions.fail();
        }
    }

    private ContoCorrente scegliContoCorrenteACaso() {
// Leggo tutti i conti correnti e ne scelgo uno a caso dalla lista appena letta
        List<ContoCorrente> contiCorrenti = contoCorrenteRepository.findAll();
        int numConti = contiCorrenti.size();
        int numContoCorrentePrescelto = random.nextInt(numConti);
        ContoCorrente tipoContoScelto = contiCorrenti.get(numContoCorrentePrescelto);
        return tipoContoScelto;
    }

    private void verificaNumeroContoCorrenteInseriti(int numeroContiCorrentiInseriti) {
// Verifico che i conti correnti siano 5
        CriterioContoCorrenteDto criterioTuttiIContiCorrenti = new CriterioContoCorrenteDto("");
        try {
            List<ContoCorrenteDto> lista = contoCorrenteService.cercaContoCorrente(criterioTuttiIContiCorrenti);
            Assertions.assertTrue(lista.size() == numeroContiCorrentiInseriti);
        } catch (SigebaException e) {
            Assertions.fail();
        }
    }

    private int creaContoCorrenteDiTest() {
// creo i conti correnti di test
        int numeroContiCorrentiInseriti = 0;
        try {
            ContoCorrenteDto contoCorrenteDto = new ContoCorrenteDto();
            contoCorrenteDto.setFido(500.00);
            contoCorrenteDto.setIban("346tre4789kj");
            contoCorrenteDto.setId(null);
            CriterioInserimentoContoCorrenteDto criterioInserimentoConto = new CriterioInserimentoContoCorrenteDto(contoCorrenteDto);
            contoCorrenteService.inserisciContoCorrente(criterioInserimentoConto);
            numeroContiCorrentiInseriti++;
            contoCorrenteDto = new ContoCorrenteDto();
            contoCorrenteDto.setFido(250.00);
            contoCorrenteDto.setIban("4uf754rc72tt");
            contoCorrenteDto.setId(null);
            criterioInserimentoConto = new CriterioInserimentoContoCorrenteDto(contoCorrenteDto);
            contoCorrenteService.inserisciContoCorrente(criterioInserimentoConto);
            numeroContiCorrentiInseriti++;
            contoCorrenteDto = new ContoCorrenteDto();
            contoCorrenteDto.setFido(500.00);
            contoCorrenteDto.setIban("ujk54r6389k4");
            contoCorrenteDto.setId(null);
            criterioInserimentoConto = new CriterioInserimentoContoCorrenteDto(contoCorrenteDto);
            contoCorrenteService.inserisciContoCorrente(criterioInserimentoConto);
            numeroContiCorrentiInseriti++;
            contoCorrenteDto = new ContoCorrenteDto();
            contoCorrenteDto.setFido(350.00);
            contoCorrenteDto.setIban("340987yhnb23");
            contoCorrenteDto.setId(null);
            criterioInserimentoConto = new CriterioInserimentoContoCorrenteDto(contoCorrenteDto);
            contoCorrenteService.inserisciContoCorrente(criterioInserimentoConto);
            numeroContiCorrentiInseriti++;
            contoCorrenteDto = new ContoCorrenteDto();
            contoCorrenteDto.setFido(150.00);
            contoCorrenteDto.setIban("plmnuter567a");
            contoCorrenteDto.setId(null);
            criterioInserimentoConto = new CriterioInserimentoContoCorrenteDto(contoCorrenteDto);
            contoCorrenteService.inserisciContoCorrente(criterioInserimentoConto);
            numeroContiCorrentiInseriti++;
        } catch (SigebaException e) {
            Assertions.fail();
        }
        return numeroContiCorrentiInseriti;
    }

    private void cancellaDatiEsistenti() {
        // cancello tutti i conti correnti
        contoCorrenteRepository.deleteAll();
    }
}
