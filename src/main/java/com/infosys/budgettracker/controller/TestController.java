/*package com.infosys.budgettracker.controller;

import com.infosys.budgettracker.model.UserEntity;
import com.infosys.budgettracker.service.UserService;
import com.infosys.budgettracker.service.JwtUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // DTO for signup request
    @Getter
    @Setter
    public static class SignupRequest {
        private String username;
        private String password;
        private String role; // USER or ADMIN
    }

    // DTO for login request
    @Getter
    @Setter
    public static class LoginRequest {
        private String username;
        private String password;
        private String role; // Optional, can verify role later
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
        UserEntity user = new UserEntity();
        user.setUsername(signupRequest.getUsername());
        user.setPassword(signupRequest.getPassword());
        user.setRole(signupRequest.getRole());

        userService.signup(user.getUsername(), user.getPassword(), user.getRole());
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = userService.login(loginRequest.getUsername(), loginRequest.getPassword());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}*/
/*package com.infosys.budgettracker.controller;

import com.infosys.budgettracker.model.UserEntity;
import com.infosys.budgettracker.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private UserService userService;

    // Signup DTO
    @Getter
    @Setter
    public static class SignupRequest {
        private String username;
        private String email;
        private String password;
        private String role; // USER or ADMIN
    }

    // Login DTO
    @Getter
    @Setter
    public static class LoginRequest {
        private String username;
        private String password;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
        try {
            userService.signup(
                    signupRequest.getUsername(),
                    signupRequest.getEmail(),
                    signupRequest.getPassword(),
                    signupRequest.getRole()
            );
            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = userService.login(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            );
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }
}

*/
package com.infosys.budgettracker.controller;

import com.infosys.budgettracker.model.UserEntity;
import com.infosys.budgettracker.service.UserService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private UserService userService;

    @Getter @Setter
    public static class SignupRequest {
        private String username;
        private String email;
        private String password;
        private String role;
    }

    @Getter @Setter
    public static class LoginRequest {
        private String username;
        private String password;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) {
        try {
            userService.signup(
                    signupRequest.getUsername(),
                    signupRequest.getEmail(),
                    signupRequest.getPassword(),
                    signupRequest.getRole()
            );
            return ResponseEntity.ok("User registered successfully!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            String token = userService.login(
                    loginRequest.getUsername(),
                    loginRequest.getPassword()
            );
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    // Protected profile endpoint
    @GetMapping("/profile")
    public ResponseEntity<String> profile(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.substring(7); // remove Bearer
            String username = userService.getUsernameFromToken(token);
            return ResponseEntity.ok("Hello, " + username + "! This is your profile.");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid token");
        }
    }
}
