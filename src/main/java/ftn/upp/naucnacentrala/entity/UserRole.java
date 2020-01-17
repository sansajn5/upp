package ftn.upp.naucnacentrala.entity;

import ftn.upp.naucnacentrala.util.enums.Role;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRole extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private Role role;
}
