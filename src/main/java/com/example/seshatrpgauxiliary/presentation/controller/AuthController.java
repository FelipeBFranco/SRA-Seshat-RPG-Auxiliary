package com.example.seshatrpgauxiliary.presentation.controller;

import com.example.seshatrpgauxiliary.application.dto.LoginRequestDTO;
import com.example.seshatrpgauxiliary.application.dto.RegisterRequestDTO;
import com.example.seshatrpgauxiliary.application.dto.ResponseDTO;
import com.example.seshatrpgauxiliary.infrastructure.persistence.entity.User;
import com.example.seshatrpgauxiliary.infrastructure.persistence.repository.UserRepository;
import com.example.seshatrpgauxiliary.infrastructure.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO body){
        User user = this.repository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("Usuario não encontrado!"));
        if(passwordEncoder.matches(body.password(), user.getPassword())) {
            String token = this.tokenService.generateToken(user);
            return ResponseEntity.ok(new ResponseDTO(user.getId(), user.getName(), token));
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDTO body) {
        Optional<User> user = this.repository.findByEmail(body.email());

        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Usuario já cadastrado!");
        }

        try {
            User newUser = new User();
            newUser.setPassword(passwordEncoder.encode(body.password()));
            newUser.setEmail(body.email());
            newUser.setName(body.name());
            this.repository.save(newUser);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cadastrar usuário!");
        }
    }
}
