package ftn.upp.naucnacentrala.domain.user;

import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public boolean checkIfUserExists(final String username, final String password) {
        ArrayList<User> users = new ArrayList<User>(userRepository.findAll());

        for(User user: users){
            if (username.equals(user.getEmail())){
                if(password.equals(user.getPassword())){
                    LoginService.currentUserId = user.getId();
                    return true;
                }
            }
        }
        return false;
    }

}
