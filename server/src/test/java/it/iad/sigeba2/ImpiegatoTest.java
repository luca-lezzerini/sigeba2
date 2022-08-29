package it.iad.sigeba2;

import it.iad.sigeba2.dto.CriterioCancellazioneImpiegatoDto;
import it.iad.sigeba2.dto.CriterioImpiegatoDto;
import it.iad.sigeba2.dto.CriterioInserimentoImpiegatoDto;
import it.iad.sigeba2.dto.ImpiegatoDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.model.Impiegato;
import it.iad.sigeba2.repository.ImpiegatoRepository;
import it.iad.sigeba2.service.ImpiegatoService;
import java.util.List;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class ImpiegatoTest {
    
    Random random = new Random();
    
    @Autowired
    ImpiegatoService impiegatoService;
    
    @Autowired
    ImpiegatoRepository impiegatoRepository;
    
    @Test
    public void testaCaricamnetoImpiegato() {
        cancellaDatiEsistenti();
        
        int numeroImpiegatiInseriti = creaImpiegatiDiTest();
        
        verificaNumeroImpiegatiInseriti(numeroImpiegatiInseriti);
        
        Impiegato impiegatoScelto = scegliImpiegatoACaso();
        
        leggeEVerificaLetturaImpiegato(impiegatoScelto);
        
        verificaRicercaPerChiaveParziale(numeroImpiegatiInseriti);
        
        verificaCancellazione(impiegatoScelto);
        
    }
    
    private void verificaCancellazione(Impiegato impiegatoScelto) {
        
        Assertions.assertThrows(
                SigebaException.class,
                () -> impiegatoService.cancellaImpiegato(null));
        
        CriterioCancellazioneImpiegatoDto criterioCancellazione = new CriterioCancellazioneImpiegatoDto();
        criterioCancellazione.setIdImpiegato(impiegatoScelto.getId());
        try {
            impiegatoService.cancellaImpiegato(criterioCancellazione);
            
        } catch (SigebaException e) {
            log.debug("Eccezione in verificaCancellazione", e);
            Assertions.fail();
            
        }
        Impiegato im = impiegatoRepository.findById(impiegatoScelto.getId())
                .map(tx -> tx)
                .orElse(null);
        Assertions.assertTrue(im == null, "Trovato record cancellato!!!");
    }
    
    private void verificaRicercaPerChiaveParziale(int numeroImpiegatiInseriti) {
        //verifico ricerca impiegato
        CriterioImpiegatoDto criterio = new CriterioImpiegatoDto();
        criterio.setCriterio("Ro");
        try {
            List<ImpiegatoDto> impiegatiTrovati = impiegatoService.cercaImpiegato(criterio);
            Assertions.assertTrue(impiegatiTrovati.size() == 2);
            for (ImpiegatoDto im : impiegatiTrovati) {
                Assertions.assertTrue(im.getNome().contains("Ro"));
            }
            
            criterio.setCriterio("r");
            impiegatiTrovati = impiegatoService.cercaImpiegato(criterio);
            Assertions.assertTrue(impiegatiTrovati.size() == numeroImpiegatiInseriti);
        } catch (SigebaException e) {
            Assertions.fail();
        }
    }
    
    private void leggeEVerificaLetturaImpiegato(Impiegato impiegatoScelto) {
        //verifico che il tipoconto con Id uguale a quello scelto abbia la descrizione "conto prova"
        SimpleIdDto sid = new SimpleIdDto();
        sid.setId(impiegatoScelto.getId());
        try {
            Impiegato im = impiegatoService.leggiImpiegato(sid);
            Assertions.assertTrue(im != null, "L'impiegato trovato è null (non trovato)");
            Assertions.assertTrue(im.getMatricola()
                    .equals(impiegatoScelto.getMatricola())
                    && im.getCognome()
                            .equals(impiegatoScelto.getCognome()));
        } catch (SigebaException e) {
            log.debug("Eccezione in leggeEVerificaLetturaImpiegato", e);
            Assertions.fail();
        }
    }
    
    private Impiegato scegliImpiegatoACaso() {
        // leggo tutti i tipi conto e ne scelgo uno a caso dalla lista appena letta
        List<Impiegato> impiegati = impiegatoRepository.findAll();
        int numTipi = impiegati.size();
        int numContoPrescelto = random.nextInt(numTipi);
        Impiegato impiegatoScelto = impiegati.get(numContoPrescelto);
        return impiegatoScelto;
    }
    
    private void verificaNumeroImpiegatiInseriti(int numeroImpiegatiInseriti) {
        //verifico che i tipi di conto siano 2
        CriterioImpiegatoDto criterioTuttiImpiegato = new CriterioImpiegatoDto("");
        try {
            List<ImpiegatoDto> lista = impiegatoService.cercaImpiegato(criterioTuttiImpiegato);
            Assertions.assertTrue(lista.size() == numeroImpiegatiInseriti);
        } catch (SigebaException e) {
            Assertions.fail();
        }
    }
    
    private int creaImpiegatiDiTest() {
        //creo i conti di test
        int numeroImpiegatiInseriti = 0;
        try {
            ImpiegatoDto impiegatoDto = new ImpiegatoDto();
            impiegatoDto.setId(null);
            impiegatoDto.setNome("Roberto");
            impiegatoDto.setCognome("Bianchi");            
            impiegatoDto.setMatricola("ht24");
            CriterioInserimentoImpiegatoDto criterioinserimento = new CriterioInserimentoImpiegatoDto(impiegatoDto);
            impiegatoService.inserisciImpiegato(criterioinserimento);
            numeroImpiegatiInseriti++;
            impiegatoDto = new ImpiegatoDto();
            impiegatoDto.setId(null);
            impiegatoDto.setNome("Rosario");
            impiegatoDto.setCognome("Rossi");
            impiegatoDto.setMatricola("er47");            
            criterioinserimento = new CriterioInserimentoImpiegatoDto(impiegatoDto);
            impiegatoService.inserisciImpiegato(criterioinserimento);
            numeroImpiegatiInseriti++;
            impiegatoDto = new ImpiegatoDto();
            impiegatoDto.setId(null);
            impiegatoDto.setNome("Angela");
            impiegatoDto.setCognome("Neri");            
            impiegatoDto.setMatricola("po99");            
            criterioinserimento = new CriterioInserimentoImpiegatoDto(impiegatoDto);
            impiegatoService.inserisciImpiegato(criterioinserimento);
            numeroImpiegatiInseriti++;
        } catch (SigebaException e) {
            log.debug("Eccezione in creaImpiegatiDiTest", e);
            Assertions.fail();
            
        }
        return numeroImpiegatiInseriti;
        
    }
    
    private void cancellaDatiEsistenti() {
        // cancello tutti i conti
        impiegatoRepository.deleteAll();
    }
    
}
