package it.iad.sigeba2.repository;

import it.iad.sigeba2.model.ContoCorrente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ContoCorrenteRepository extends JpaRepository<ContoCorrente, Long> {

    List<ContoCorrente> findByIbanLike(String critIban);

    @Query(value = "select count(*) from conto_corrente where cliente_id = :idCli",
            nativeQuery = true)
    int contaCCPerCliente(Long idCli);

    @Query(value = "select count(cc) from ContoCorrente cc where cc.cliente.id = :idCli")
    int contaCCPerClienteJPQL(Long idCli);
    
    @Query(value = "select count(cc) from ContoCorrente cc where cc.filiale.id = :idFiliale")
    int contaCCPerFilialeJPQL(Long idFiliale);
    
}
