package com.medibook.controller;


import com.medibook.controller.request.CreateUserDTO;
import com.medibook.entities.Role;
import com.medibook.entities.UserEntity;
import com.medibook.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class PrincipalController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;



    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserDTO createUserDTO){
    //Creo el usuario
        UserEntity userEntity = UserEntity.builder()
                .username(createUserDTO.getUsername())
                .password(passwordEncoder.encode(createUserDTO.getPassword()))
                .name(createUserDTO.getName())
                .lastname(createUserDTO.getLastname())
                .role(Role.USER)
                .build();

    //Persisto el usuario y lo retorno
        userRepository.save(userEntity);

        return ResponseEntity.ok(userEntity);
    }

    /*@DeleteMapping("/deleteUser")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteUser(@RequestParam String id){
        userRepository.deleteById(Long.parseLong(id));
        return "Se ha borrado el user con id ".concat(id);

    }*/
}
