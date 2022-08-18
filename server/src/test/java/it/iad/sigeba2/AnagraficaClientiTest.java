package it.iad.sigeba2;

import it.iad.sigeba2.dto.ClienteDto;
import it.iad.sigeba2.dto.CriterioCancellazioneClienteDto;
import it.iad.sigeba2.dto.CriterioClienteDto;
import it.iad.sigeba2.dto.CriterioInserimentoClienteDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.model.Cliente;
import it.iad.sigeba2.repository.ClienteRepository;
import it.iad.sigeba2.service.AnagraficaClientiService;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnagraficaClientiTest {

    Random random = new Random();
    @Autowired
    AnagraficaClientiService anagraficaClientiService;

    @Autowired
    ClienteRepository clienteRepository;

    @Test
    public void testaCaricamentoClienti() {
        cancellaDatiEsistenti();

        int numeroClientiInseriti = creaClientiDiTest();
        verificaNumeroClientiInseriti(numeroClientiInseriti);
        Cliente clienteScelto = scegliClienteACaso();
        leggiEVerificaLetturaCliente(clienteScelto);
        verificaRicercaPerChiaveParziale(numeroClientiInseriti);
        verificaCancellazioneCliente(clienteScelto);
    }

    private void verificaCancellazioneCliente(Cliente clienteScelto) {
        //Verifico cancellazione cliente
        Assertions.assertThrows(
                SigebaException.class,
                () -> anagraficaClientiService.cancellaCliente(null));
        CriterioCancellazioneClienteDto criterioCancellazioneCliente = new CriterioCancellazioneClienteDto();
        criterioCancellazioneCliente.setIdCliente(clienteScelto.getId());
        try {
            anagraficaClientiService.cancellaCliente(criterioCancellazioneCliente);
        } catch (SigebaException e) {
            Assertions.fail();
        }
        Cliente cli = clienteRepository.findById(clienteScelto.getId())
                .map(tx -> tx)
                .orElse(null);
        Assertions.assertTrue(cli == null, "Trovato record cancellato!!");
    }

    private void verificaRicercaPerChiaveParziale(int numeroClientiInseriti) {
        //Verifico ricerca cliente
        CriterioClienteDto criterio = new CriterioClienteDto();
        criterio.setCriterio("to");
        try {
            List<ClienteDto> clientiTrovati = anagraficaClientiService.cercaCliente(criterio);
            Assertions.assertTrue(clientiTrovati.size() == 1);
            for (ClienteDto cli : clientiTrovati) {
                Assertions.assertTrue(cli.getNome().contains("to"));
            }
            criterio.setCriterio("");
            clientiTrovati = anagraficaClientiService.cercaCliente(criterio);
            Assertions.assertTrue(clientiTrovati.size() == numeroClientiInseriti);
        } catch (SigebaException e) {
            Assertions.fail();
        }
    }

    private void leggiEVerificaLetturaCliente(Cliente clienteScelto) {
        //Verifico che il cliente con Id uguale a quello scelto abbia il cognome "Bianchi"
        SimpleIdDto sid = new SimpleIdDto();
        sid.setId(clienteScelto.getId());
        try {
            Cliente cli = anagraficaClientiService.leggiCliente(sid);
            Assertions.assertTrue(cli != null);
            Assertions.assertTrue(cli.getCognome()
                    .equals(clienteScelto.getCognome())
                    && cli.getCodiceFiscale()
                            .equals(clienteScelto.getCodiceFiscale()));
        } catch (SigebaException e) {
            Assertions.fail();
        }
    }

    private Cliente scegliClienteACaso() {
        //leggo tutti i clienti e ne scelgo uno a caso dalla lista appena letta
        List<Cliente> cliente = clienteRepository.findAll();
        int numCli = cliente.size();
        int numCliPrescelto = random.nextInt(numCli);
        Cliente clienteScelto = cliente.get(numCliPrescelto);
        return clienteScelto;
    }

    private void verificaNumeroClientiInseriti(int numeroClientiInseriti) {
        //Verifico che i clienti siano 3
        CriterioClienteDto criterioTuttiIClienti = new CriterioClienteDto("");
        try {
            List<ClienteDto> lista = anagraficaClientiService.cercaCliente(criterioTuttiIClienti);
            Assertions.assertTrue(lista.size() == numeroClientiInseriti);
        } catch (SigebaException e) {
            Assertions.fail();
        }
    }

    private int creaClientiDiTest() {
        //Crei i clienti di test
        int numeroClientiInseriti = 0;
        try {
            ClienteDto clienteDto = new ClienteDto();
            clienteDto.setCodiceFiscale("ABCD");
            clienteDto.setCognome("Rossi");
            clienteDto.setId(null);
            clienteDto.setNome("Mario");
            CriterioInserimentoClienteDto criterioInserimento = new CriterioInserimentoClienteDto(clienteDto);
            anagraficaClientiService.inserisciCliente(criterioInserimento);
            numeroClientiInseriti++;
            clienteDto.setCodiceFiscale("EFGH");
            clienteDto.setCognome("Bianchi");
            clienteDto.setId(null);
            clienteDto.setNome("Antonio");
            criterioInserimento = new CriterioInserimentoClienteDto(clienteDto);
            anagraficaClientiService.inserisciCliente(criterioInserimento);
            numeroClientiInseriti++;
            clienteDto.setCodiceFiscale("IJKL");
            clienteDto.setCognome("Verdi");
            clienteDto.setId(null);
            clienteDto.setNome("Elisa");
            criterioInserimento = new CriterioInserimentoClienteDto(clienteDto);
            anagraficaClientiService.inserisciCliente(criterioInserimento);
            numeroClientiInseriti++;

        } catch (SigebaException e) {
            Assertions.fail();
        }
        return numeroClientiInseriti;
    }

    private void cancellaDatiEsistenti() {
        //Cancello tutti i clienti
        clienteRepository.deleteAll();
    }
}
