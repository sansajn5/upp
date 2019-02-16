package ftn.upp.naucnacentrala.domain.scientificField;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ScientificField {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String scientificFieldName;

    public ScientificField(final String name) {
        this.scientificFieldName = name;
    }

    public ScientificField () {}

    public String getScientificFieldName() {
        return scientificFieldName;
    }

    public void setScientificFieldName(String scientificFieldName) {
        this.scientificFieldName = scientificFieldName;
    }

}
