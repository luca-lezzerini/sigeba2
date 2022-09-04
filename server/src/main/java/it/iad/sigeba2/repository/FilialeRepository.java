package it.iad.sigeba2.repository;

import it.iad.sigeba2.model.Filiale;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilialeRepository extends JpaRepository<Filiale, Long> {

    List<Filiale> findByNomeLikeOrCodiceLike(String critNome, String critCodice);
    
    }
