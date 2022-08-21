package it.iad.sigeba2.repository;

import it.iad.sigeba2.model.ContoCorrente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContoCorrenteRepository extends JpaRepository<ContoCorrente, Long> {

    List<ContoCorrente> findByIbanLike(String critIban);

}
