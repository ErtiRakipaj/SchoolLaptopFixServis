package com.task.SchoolLaptopFixServis.services;

import com.task.SchoolLaptopFixServis.models.Role;
import com.task.SchoolLaptopFixServis.models.User;
import com.task.SchoolLaptopFixServis.repositories.RoleRepository;
import com.task.SchoolLaptopFixServis.repositories.UserRepository;
import com.task.SchoolLaptopFixServis.responses.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;


    public User registerUser(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findRoleByAuthority("USER").get();

        Set<Role> authorities = new HashSet<>();

        authorities.add(userRole);

        return userRepository.save(new User(username,encodedPassword,authorities));
    }

    public LoginResponse loginUser(String username, String password) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponse(userRepository.findUserByUsername(username).get(), token);

        } catch (AuthenticationException e) {
            return new LoginResponse(null,"");
        }
    }
}
