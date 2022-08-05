package it.iad.sigeba2.repository;

import it.iad.sigeba2.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNomeLikeOrCognomeLikeOrCodiceFiscaleLike(String critNome, String critCognome, String critCodFisc);
}
