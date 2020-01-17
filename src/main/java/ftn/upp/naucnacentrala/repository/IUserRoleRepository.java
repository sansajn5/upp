package ftn.upp.naucnacentrala.repository;

import ftn.upp.naucnacentrala.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRoleRepository extends JpaRepository<UserRole, Long> {

}
