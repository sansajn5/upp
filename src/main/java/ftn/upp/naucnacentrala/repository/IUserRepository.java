package ftn.upp.naucnacentrala.repository;

import ftn.upp.naucnacentrala.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

}
