package ftn.upp.naucnacentrala.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="UserEntity")
public class User extends BaseEntity {

    private String email;

    private String firstName;

    private String lastName;

    private String country;

    private String city;

    private String username;

    private String password;

    @OneToOne
    private UserRole role;

}
