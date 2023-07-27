package te4rus.ru.scribe.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import te4rus.ru.scribe.domain.Role;
import te4rus.ru.scribe.domain.User;
import te4rus.ru.scribe.exception.UserNotFound;
import te4rus.ru.scribe.repository.RoleRepository;
import te4rus.ru.scribe.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final ApplicationContext applicationContext;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username);
    }

    public User findUserById(Long id) {
        return userRepository
                .findById(id)
                .orElseThrow(() -> new UserNotFound(id));
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    public boolean saveUser(User user) {
        log.debug("Save new user: " + user);

        if (userRepository.findByUsername(user.getUsername()) != null) {
            log.warn(String.format("User with name:%s already exists", user.getUsername()));
            return false;
        }

        user.setPassword(applicationContext.getBean(PasswordEncoder.class).encode(user.getPassword()));
        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        userRepository.save(user);
        log.debug("User success saving");
        return true;
    }
}
