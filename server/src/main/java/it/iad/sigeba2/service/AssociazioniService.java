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

    void associaContoACliente(Long idCliente, Long idConto) throws SigebaException;

    void disassociaContoDaCliente(Long idCliente, Long idConto) throws SigebaException;

    void associaFilialeAConto(Long idConto, Long idFiliale) throws SigebaException;

    void disassociaFilialeDaConto(Long idConto, Long idFiliale) throws SigebaException;
    
    void associaTipoContoAConto(Long idConto,Long idTipoConto) throws SigebaException;
    
    void disassociaTipoContoDaConto(Long idConto,Long idTipoConto) throws SigebaException;


    void associaContoATipoConto(Long idTipoConto,Long idConto) throws SigebaException;

    void disassociaContoDaTipoConto(Long idTipoConto,Long idConto)throws SigebaException;

    
    void associaContoAFiliale(Long idFiliale, Long idConto ) throws SigebaException;

    void disassociaContoDaFiliale(Long idFiliale, Long idConto ) throws SigebaException;
    

}
