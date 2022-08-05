package it.iad.sigeba2;

import it.iad.sigeba2.dto.ClienteDto;
import it.iad.sigeba2.dto.CriterioClienteDto;
import it.iad.sigeba2.dto.CriterioInserimentoClienteDto;
import it.iad.sigeba2.dto.SimpleIdDto;
import it.iad.sigeba2.model.Cliente;
import it.iad.sigeba2.repository.ClienteRepository;
import it.iad.sigeba2.service.AnagraficaClientiService;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AnagraficaClientiTest {

    @Autowired
    AnagraficaClientiService anagraficaClientiService;

    @Autowired
    ClienteRepository clienteRepository;

    @Test
    public void testaCaricamentoClienti() {
        // cancello tutti i clienti
        clienteRepository.deleteAll();

        // creo i clienti di test
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setCodiceFiscale("ABCD");
        clienteDto.setCognome("Rossi");
        clienteDto.setId(null);
        clienteDto.setNome("Mario");
        CriterioInserimentoClienteDto criterioInserimento = new CriterioInserimentoClienteDto(clienteDto);
        anagraficaClientiService.inserisciCliente(criterioInserimento);
        clienteDto = new ClienteDto();
        clienteDto.setCodiceFiscale("EFGH");
        clienteDto.setCognome("Bianchi");
        clienteDto.setId(null);
        clienteDto.setNome("Antonio");
        criterioInserimento = new CriterioInserimentoClienteDto(clienteDto);
        anagraficaClientiService.inserisciCliente(criterioInserimento);
        clienteDto = new ClienteDto();
        clienteDto.setCodiceFiscale("IJKL");
        clienteDto.setCognome("Verdi");
        clienteDto.setId(null);
        clienteDto.setNome("Elisa");
        criterioInserimento = new CriterioInserimentoClienteDto(clienteDto);
        anagraficaClientiService.inserisciCliente(criterioInserimento);

        // verifico che i clienti siano 3
        CriterioClienteDto criterioTutti = new CriterioClienteDto("");
        List<ClienteDto> lista = anagraficaClientiService.cercaCliente(criterioTutti);
        Assertions.assertTrue(lista.size() == 3);
        // verifico che cliente con id 0 sia Antonio Bianchi
        SimpleIdDto sid = new SimpleIdDto();
        sid.setId(2L);
        Cliente cli = anagraficaClientiService.leggiCliente(sid);
        Assertions.assertTrue(cli != null);
        Assertions.assertTrue(cli.getNome().equals("Antonio") && cli.getCognome().equals("Bianchi"));

        // verifico ricerca cliente
        CriterioClienteDto criterio = new CriterioClienteDto();
        criterio.setCriterio("oss");
        List<ClienteDto> clientiTrovati = anagraficaClientiService.cercaCliente(criterio);
        Assertions.assertTrue(clientiTrovati.size() == 1);
        Assertions.assertTrue(clientiTrovati.get(0).getCognome().equals("Rossi"));
        criterio.setCriterio("r");
        clientiTrovati = anagraficaClientiService.cercaCliente(criterio);
        Assertions.assertTrue(clientiTrovati.size() == 2);
    }
}
