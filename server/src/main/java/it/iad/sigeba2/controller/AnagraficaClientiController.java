package it.iad.sigeba2.controller;

import it.iad.sigeba2.dto.CriterioClienteDto;
import it.iad.sigeba2.dto.ClienteDto;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnagraficaClientiController {

    @RequestMapping("/cercaCliente")
    public List<ClienteDto> cercaCliente(CriterioClienteDto criterio) {
        throw new UnsupportedOperationException();
    }
}
