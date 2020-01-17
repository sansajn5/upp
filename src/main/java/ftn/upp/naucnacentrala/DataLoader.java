package ftn.upp.naucnacentrala;

import ftn.upp.naucnacentrala.entity.User;
import ftn.upp.naucnacentrala.entity.UserRole;
import ftn.upp.naucnacentrala.repository.IUserRepository;
import ftn.upp.naucnacentrala.repository.IUserRoleRepository;
import ftn.upp.naucnacentrala.util.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private IUserRepository _userRepository;

    @Autowired
    private IUserRoleRepository _userRoleRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserRole userRole = new UserRole();
        userRole.setRole(Role.USER);
        _userRoleRepository.save(userRole);

        User user = new User();
        user.setUsername("nemanja");
        user.setRole(userRole);
        _userRepository.save(user);
    }
}
