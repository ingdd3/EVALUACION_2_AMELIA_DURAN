package com.platinum.configuration;

import com.platinum.model.Person;
import com.platinum.model.User;
import com.platinum.repository.IPersonRepository;
import com.platinum.repository.IUserRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Configuration
@Order(1)
public class UserInitializer {

    private final IUserRepository userRepository;

    private final IPersonRepository personRepository;

    private final PasswordEncoder passwordEncoder;


    public UserInitializer(IUserRepository userRepository, IPersonRepository personRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        Person person = new Person("123456789-L", "Amelia", "Doe", "1234567890", LocalDate.now());
        User user = new User();
        user.setUsername("prueba@prueba.com");
        user.setPassword(passwordEncoder.encode("12345678")); // Aplica el password encoder al password
        user.setPerson(person);
        if (userRepository.findByUsername(user.getUsername()).isEmpty()) {
            this.personRepository.save(person);
            this.userRepository.save(user);
        }
    }

}
