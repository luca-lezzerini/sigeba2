package it.iad.sigeba2.service.impl;

import it.iad.sigeba2.enumerated.MessaggioStatoEnum;
import it.iad.sigeba2.exception.SigebaException;
import it.iad.sigeba2.helper.SigebaStateCollector;
import it.iad.sigeba2.model.Cliente;
import it.iad.sigeba2.model.ContoCorrente;
import it.iad.sigeba2.model.TipoConto;
import it.iad.sigeba2.repository.ClienteRepository;
import it.iad.sigeba2.repository.ContoCorrenteRepository;
import it.iad.sigeba2.repository.TipoContoRepository;
import it.iad.sigeba2.service.AssociazioniService;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class AssociazioniServiceImpl implements AssociazioniService {

    @Autowired
    TipoContoRepository tipoContoRepository;
    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    ContoCorrenteRepository contoCorrenteRepository;

    @Transactional(rollbackFor = {SigebaException.class})
    @Override
    public void associaClienteAConto(Long idConto, Long idCliente) throws SigebaException {

        //verificare la correttezza degli input
        //devono essere non null
        if (idConto == null || idCliente == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.ENTITA_DA_ASSOCIARE_NULL);
            throw new SigebaException("idConto o idCliente null");
        }
        //devono esistere sul db
        Cliente cliente = clienteRepository.findById(idCliente)
                .map(it -> it)
                .orElseThrow(() -> {
                    SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.CLIENTE_NON_TROVATO);
                    return new SigebaException("Cliente non trovato");
                });
        ContoCorrente contoCorrente = contoCorrenteRepository.findById(idConto)
                .map(it -> it)
                .orElseThrow(() -> {
                    SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.CONTO_CORRENTE_NON_TROVATO);
                    return new SigebaException("Conto Corrente non trovato");
                });

        //associamo i due oggetti
        contoCorrente.setCliente(cliente);
        contoCorrenteRepository.save(contoCorrente);
        //   throw new SigebaException();
    }

    @Transactional(rollbackFor = {SigebaException.class})
    @Override
    public void disassociaClienteDaConto(Long idConto, Long idCliente) throws SigebaException {

        //verificare la correttezza degli input
        //devono essere non null
        if (idConto == null || idCliente == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.ENTITA_DA_ASSOCIARE_NULL);
            throw new SigebaException("idConto o idCliente null");
        }
        //devono esistere sul db
        Cliente cliente = clienteRepository.findById(idCliente)
                .map(it -> it)
                .orElseThrow(() -> {
                    SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.CLIENTE_NON_TROVATO);
                    return new SigebaException("Cliente non trovato");
                });
        ContoCorrente contoCorrente = contoCorrenteRepository.findById(idConto)
                .map(it -> it)
                .orElseThrow(() -> {
                    SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.CONTO_CORRENTE_NON_TROVATO);
                    return new SigebaException("Conto Corrente non trovato");
                });

        if (cliente == contoCorrente.getCliente()) {
            //disassociamo i due oggetti
            contoCorrente.setCliente(null);
            contoCorrenteRepository.save(contoCorrente);
        } else {
            throw new SigebaException("Cliente e ContoCorrente non corrispondono");
        }
    }

    @Transactional(rollbackFor = {SigebaException.class})
    @Override
    public void associaContoACliente(Long idCliente, Long idConto) throws SigebaException {

        //verificare la correttezza degli input
        //devono essere non null
        if (idCliente == null || idConto == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.ENTITA_DA_ASSOCIARE_NULL);
            throw new SigebaException("idConto o idCliente null");
        }
        //devono esistere sul db
        Cliente cliente = clienteRepository.findById(idCliente)
                .map(it -> it)
                .orElseThrow(() -> {
                    SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.CLIENTE_NON_TROVATO);
                    return new SigebaException("Cliente non trovato");
                });
        ContoCorrente contoCorrente = contoCorrenteRepository.findById(idConto)
                .map(it -> it)
                .orElseThrow(() -> {
                    SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.CONTO_CORRENTE_NON_TROVATO);
                    return new SigebaException("Conto Corrente non trovato");
                });

        //associamo i due oggetti
        List<ContoCorrente> conti;
        conti = cliente.getContiCorrenti();
        log.debug("Cerco la lista conti");
        if (conti == null) {
            log.debug("Lista null, quindi creo una nuova lista");
            conti = new ArrayList<>();

        }

        associaClienteAConto(idConto, idCliente);
        conti.add(contoCorrente);
        clienteRepository.save(cliente);

        //   throw new SigebaException();
    }

    /**
     *
     * @param idCliente
     * @param idConto
     * @throws SigebaException
     */
    @Transactional(rollbackFor = {SigebaException.class})
    @Override
    public void disassociaContoDaCliente(Long idCliente, Long idConto) throws SigebaException {

        //verificare la correttezza degli input
        //devono essere non null
        if (idConto == null || idCliente == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.ENTITA_DA_ASSOCIARE_NULL);
            throw new SigebaException("idConto o idCliente null");
        }
        //devono esistere sul db
        Cliente cliente = clienteRepository.findById(idCliente)
                .map(it -> it)
                .orElseThrow(() -> {
                    SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.CLIENTE_NON_TROVATO);
                    return new SigebaException("Cliente non trovato");
                });
        ContoCorrente contoCorrente = contoCorrenteRepository.findById(idConto)
                .map(it -> it)
                .orElseThrow(() -> {
                    SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.CONTO_CORRENTE_NON_TROVATO);
                    return new SigebaException("Conto Corrente non trovato");
                });
        List<ContoCorrente> contiCliente = cliente.getContiCorrenti();

        if (contiCliente.contains(contoCorrente)) {
            //disassociamo i due oggetti
            contiCliente.remove(contoCorrente);
            disassociaClienteDaConto(idConto, idCliente);
            clienteRepository.save(cliente);
        } else {
            throw new SigebaException("Cliente e ContoCorrente non corrispondono");
        }
    }

    @Transactional(rollbackFor = {SigebaException.class})
    @Override
    public void associaTipoContoAConto(Long idConto, Long idTipoconto) throws SigebaException {

        //verificare la correttezza degli input
        //devono essere non null
        if (idConto == null || idTipoconto == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.ENTITA_DA_ASSOCIARE_NULL);
            throw new SigebaException("idConto o idCliente null");
        }
        //devono esistere sul db
        TipoConto tipoConto = tipoContoRepository.findById(idTipoconto)
                .map(it -> it)
                .orElseThrow(() -> {
                    SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.TIPO_CONTO_NON_TROVATO);
                    return new SigebaException("TipoConto non trovato");
                });
        ContoCorrente contoCorrente = contoCorrenteRepository.findById(idConto)
                .map(it -> it)
                .orElseThrow(() -> {
                    SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.CONTO_CORRENTE_NON_TROVATO);
                    return new SigebaException("Conto Corrente non trovato");
                });

        //associamo i due oggetti
        contoCorrente.setTipo(tipoConto);
        contoCorrenteRepository.save(contoCorrente);
        //   throw new SigebaException();
    }

    @Transactional(rollbackFor = {SigebaException.class})
    @Override
    public void disassociaTipoContoDaConto(Long idConto, Long idTipoConto) throws SigebaException {
        //verificare la correttezza degli input
        //devono essere non null
        if (idConto == null || idTipoConto == null) {
            SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.ENTITA_DA_ASSOCIARE_NULL);
            throw new SigebaException("idConto o idTipoConto null");
        }
        //devono esistere sul db
        TipoConto tipoConto = tipoContoRepository.findById(idTipoConto)
                .map(it -> it)
                .orElseThrow(() -> {
                    SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.TIPO_CONTO_NON_TROVATO);
                    return new SigebaException("TipoConto non trovato");
                });
        ContoCorrente contoCorrente = contoCorrenteRepository.findById(idConto)
                .map(it -> it)
                .orElseThrow(() -> {
                    SigebaStateCollector.addStatusMessage(MessaggioStatoEnum.CONTO_CORRENTE_NON_TROVATO);
                    return new SigebaException("Conto Corrente non trovato");
                });

        if (tipoConto == contoCorrente.getTipo()) {
            //disassociamo i due oggetti
            contoCorrente.setTipo(null);
            contoCorrenteRepository.save(contoCorrente);
        } else {
            throw new SigebaException("TipoConto e ContoCorrente non corrispondono");
        }
    }














}
