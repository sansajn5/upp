package ftn.upp.naucnacentrala.domain.magazine;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ftn.upp.naucnacentrala.domain.scientificField.ScientificField;
import ftn.upp.naucnacentrala.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class Magazine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotEmpty
    private String name;

    private int ISSN;

    @ManyToMany
    private Set<ScientificField> scientificFields;

    @JoinTable(name ="magazine_reviewers")
    @ManyToMany
    private Set<User> reviewers;

    // recenzenti
    @JoinTable(name ="magazine_editors")
    @ManyToMany
    private Set<User> editorsOfSpecificAreas;

    @NotNull
    private double price;

    @ManyToOne(optional = false)
    @JoinColumn(name="main_editor_id")
    @NotNull
    @JsonIgnore
    private User mainEditor;

    public Magazine(
        final String name,
        final double price,
        final User mainEditor,
        final Set<ScientificField> scientificFields
    ) {
        this.name = name;
        this.price = price;
        this.mainEditor = mainEditor;
        this.scientificFields = scientificFields;
    }

    public Magazine() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getISSN() {
        return ISSN;
    }

    public void setISSN(int ISSN) {
        this.ISSN = ISSN;
    }

    public Set<ScientificField> getScientificFields() {
        return scientificFields;
    }

    public void setScientificFields(Set<ScientificField> scientificFields) {
        this.scientificFields = scientificFields;
    }

    public Set<User> getReviewers() {
        return reviewers;
    }

    public void setReviewers(Set<User> reviewers) {
        this.reviewers = reviewers;
    }

    public Set<User> getEditorsOfSpecificAreas() {
        return editorsOfSpecificAreas;
    }

    public void setEditorsOfSpecificAreas(Set<User> editorsOfSpecificAreas) {
        this.editorsOfSpecificAreas = editorsOfSpecificAreas;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getMainEditor() {
        return mainEditor;
    }

    public void setMainEditor(User mainEditor) {
        this.mainEditor = mainEditor;
    }

}
