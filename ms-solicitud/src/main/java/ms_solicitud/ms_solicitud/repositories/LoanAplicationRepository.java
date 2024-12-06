package ms_solicitud.ms_solicitud.repositories;

import ms_solicitud.ms_solicitud.entities.LoanAplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanAplicationRepository extends JpaRepository<LoanAplicationEntity, Long> {

    public List<LoanAplicationEntity> findByState(String state);

    public ArrayList<LoanAplicationEntity> findAllByRutUser(String rutUser);

    public Optional<LoanAplicationEntity> findById(Long id);

}
