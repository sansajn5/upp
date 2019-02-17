package ftn.upp.naucnacentrala.domain.notification;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @NotEmpty
    private String description;

    @NotNull
    private Long recieverId;

    public Notification() {}

    public Notification(final String description, final Long recieverId){
        this.description = description;
        this.recieverId = recieverId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getRecieverId() {
        return recieverId;
    }

    public void setRecieverId(Long recieverId) {
        this.recieverId = recieverId;
    }
}
