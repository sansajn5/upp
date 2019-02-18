package ftn.upp.naucnacentrala.domain.scientificWork;

import ftn.upp.naucnacentrala.domain.magazine.Magazine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScientificWorkRepository  extends JpaRepository<ScientificWork, Long> {

}
