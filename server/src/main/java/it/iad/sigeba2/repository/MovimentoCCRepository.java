package it.iad.sigeba2.repository;

import it.iad.sigeba2.model.MovimentoCC;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimentoCCRepository extends JpaRepository<MovimentoCC, Long> {

    List<MovimentoCC> findByTipoLikeOrDescrizioneLike(String critTipo, String critDescr);

}
