package com.platinum.service;

import com.platinum.model.User;
import com.platinum.repository.IUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Resource
    private final IUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(IUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Map<String, String> verifyLogin(String username, String password) {
        Map<String, String> result = new HashMap<>();
        Optional<User> userOptional = userRepository.findByUsername(username);
        if(username.isEmpty() || password.isEmpty()){
            result.put("Campos vacíos: ", "Verifica que los campos no sean vacíos.");
            return result;
        }
        if (userOptional.isEmpty()) {
            result.put("Usuario no encontrado: ", "Verifica el nombre de usuario ingresado.");
            return result;
        }
        User user = userOptional.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            result.put("Contraseña incorrecta", "Verifica la contraseña ingresada.");
            return result;
        }
        return result;
    }

    @Override
    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username).orElse(null);
    }

    public void saveUser(User user) {
        user.setPassword(user.getPassword());
        userRepository.save(user);
    }
}