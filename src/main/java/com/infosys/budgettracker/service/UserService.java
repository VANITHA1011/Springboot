/*package com.infosys.budgettracker.service;

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
}*/
/*package com.infosys.budgettracker.service;

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

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Signup
    public UserEntity signup(String username, String email, String password, String role) throws Exception {

        if (userRepository.findByUsername(username).isPresent()) {
            throw new Exception("Username already exists");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new Exception("Email already exists");
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        return userRepository.save(user);
    }

    // Login
    public String login(String username, String password) throws Exception {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new Exception("User not found");
        }

        UserEntity user = optionalUser.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("Invalid Password");
        }

        return jwtUtil.generateToken(username);
    }
}
*/
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

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Signup
    public UserEntity signup(String username, String email, String password, String role) throws Exception {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new Exception("Username already exists");
        }
        if (userRepository.findByEmail(email).isPresent()) {
            throw new Exception("Email already exists");
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);

        return userRepository.save(user);
    }

    // Login
    public String login(String username, String password) throws Exception {
        Optional<UserEntity> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) throw new Exception("User not found");

        UserEntity user = optionalUser.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("Invalid Password");
        }

        return jwtUtil.generateToken(username);
    }

    public String getUsernameFromToken(String token) {
        return jwtUtil.extractUsername(token);
    }
}
