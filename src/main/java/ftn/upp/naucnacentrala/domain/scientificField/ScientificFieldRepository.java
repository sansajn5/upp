package ftn.upp.naucnacentrala.domain.scientificField;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ScientificFieldRepository extends JpaRepository<ScientificField, Long> {
    ScientificField findByScientificFieldName(final String name);
}
