package it.iad.sigeba2.service;

import it.iad.sigeba2.exception.SigebaException;

public interface AssociazioniService {

    /**
     * Dato un conto corrente gli associa un cliente
     *
     * @param idCliente da associare al conto
     * @param idConto a cui associare il cliente
     * @throws it.iad.sigeba2.exception.SigebaException
     */
    void associaClienteAConto(Long idConto, Long idCliente) throws SigebaException;

    void disassociaClienteDaConto(Long idConto, Long idCliente) throws SigebaException;

}
