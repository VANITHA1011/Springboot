package com.infosys.budgettracker.service;

import com.infosys.budgettracker.model.UserEntity;
import com.infosys.budgettracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Signup with username, password, role
    public UserEntity signup(String username, String password, String role) {
        String encryptedPassword = passwordEncoder.encode(password);

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(encryptedPassword);
        user.setRole(role); // Set role from request

        return userRepository.save(user);
    }

    // Login returns JWT token
    public String login(String username, String password) throws Exception {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            UserEntity user = optionalUser.get();

            if (passwordEncoder.matches(password, user.getPassword())) {
                // You can also include role in token if needed
                return jwtUtil.generateToken(username);
            } else {
                throw new Exception("Invalid Password");
            }

        } else {
            throw new Exception("User not found");
        }
    }
}
