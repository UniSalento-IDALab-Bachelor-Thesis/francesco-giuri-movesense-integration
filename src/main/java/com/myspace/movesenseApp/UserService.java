package com.myspace.movesenseApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Registrazione dell'utente
    public String registerUser(UserEntity userEntity) {
        // Verifica se l'email è già registrata
        if (userRepository.findByEmail(userEntity.getEmail()) != null) {
            return "Email già in uso";
        }

        // Crittografa la password prima di salvarla
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        // Salva l'utente nel database
        userRepository.save(userEntity);
        return "Registrazione completata con successo";
    }

    // Login dell'utente
    public String loginUser(String email, String password) {
        UserEntity user = userRepository.findByEmail(email);

        if (user == null) {
            return "Utente non trovato";
        }

        // Confronta la password crittografata
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return "Password errata";
        }

        return "Login avvenuto con successo";
    }

    // Metodo per ottenere l'ID utente dall'email
    public Long getUserIdByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user != null) {
            return user.getId(); // Restituisce l'ID dell'utente
        }
        return null; // Se l'utente non esiste, restituisce null
    }
}
