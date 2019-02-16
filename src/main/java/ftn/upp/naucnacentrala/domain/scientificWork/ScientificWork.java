package ftn.upp.naucnacentrala.domain.scientificWork;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import ftn.upp.naucnacentrala.domain.magazine.Magazine;
import ftn.upp.naucnacentrala.domain.scientificField.ScientificField;
import ftn.upp.naucnacentrala.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class ScientificWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotEmpty
    private String title;

    private String keywords;

    private String abstractDescription;

    private String pdf;

    @JoinTable(name = "scientific_work_co_authors")
    @ManyToMany
    private Set<User> coAuthors;

    @ManyToOne(optional = false)
    @JoinColumn(name="scientific_field_id")
    @NotNull
    @JsonIgnore
    private ScientificField scientificField;

    @ManyToOne(optional = false)
    @JoinColumn(name="author_id")
    @NotNull
    @JsonIgnore
    private User author;

    @ManyToOne(optional = false)
    @JoinColumn(name="magazine_id")
    @NotNull
    @JsonIgnore
    private Magazine magazine;

    public ScientificWork() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getAbstractDescription() {
        return abstractDescription;
    }

    public void setAbstractDescription(String abstractDescription) {
        this.abstractDescription = abstractDescription;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public Set<User> getCoAuthors() {
        return coAuthors;
    }

    public void setCoAuthors(Set<User> coAuthors) {
        this.coAuthors = coAuthors;
    }

    public ScientificField getScientificField() {
        return scientificField;
    }

    public void setScientificField(ScientificField scientificField) {
        this.scientificField = scientificField;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Magazine getMagazine() {
        return magazine;
    }

    public void setMagazine(Magazine magazine) {
        this.magazine = magazine;
    }
}
