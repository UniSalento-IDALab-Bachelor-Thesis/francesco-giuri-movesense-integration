package com.myspace.movesenseApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout(HttpSession session) {
        Map<String, String> response = new HashMap<>();
        response.put("email", "guest");
        // Invalida la sessione per rimuovere l'utente
        session.invalidate();
        // Restituisci la risposta
        return ResponseEntity.ok(response);
    }


    @GetMapping("/check-session")
    public ResponseEntity<Map<String, String>> checkSession(HttpSession session) {
        String userEmail = (String) session.getAttribute("userEmail");
        Map<String, String> response = new HashMap<>();

        if (userEmail != null) {
            response.put("email", userEmail);
        } else {
            response.put("email", "guest");
        }
        return ResponseEntity.ok(response);
    }

    // Registrazione utente
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserEntity userEntity) {
        String result = userService.registerUser(userEntity);
        if ("Registrazione completata con successo".equals(result)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Registrazione avvenuta con successo");
        }
        return ResponseEntity.badRequest().body(result);
    }

    // Login utente
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginCredentials loginCredentials, HttpSession session) {
        String result = userService.loginUser(loginCredentials.getEmail(), loginCredentials.getPassword());

        // Controlla se il login è avvenuto con successo
        if ("Login avvenuto con successo".equals(result)) {
            // Salva i dati dell'utente nella sessione
            session.setAttribute("userEmail", loginCredentials.getEmail());
            return ResponseEntity.ok(new LoginResponse("Login avvenuto con successo", "/dashboard.html"));
        }

        return ResponseEntity.badRequest().body(new LoginResponse(result, null));
    }

    @GetMapping("/get-id")
    public ResponseEntity<Map<String, Object>> getUserIdByEmail(@RequestParam String email) {
        //System.out.println("Richiesta di User ID per Email: " + email);
        Map<String, Object> response = new HashMap<>();

        try {
            Long userId = userService.getUserIdByEmail(email);
            //System.out.println("Email ricevuta: " + email + ", UserID trovato: " + userId);

            if (userId != null) {
                response.put("userId", userId);
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Utente non trovato");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
        } catch (Exception e) {
            response.put("message", "Errore durante il recupero dell'ID utente");
            System.err.println("Eccezione: " + e.getMessage()); // Log dell'errore
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }


    // Classe interna per le credenziali di login
    public static class LoginCredentials {
        private String email;
        private String password;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    // Classe di risposta per il login
    public static class LoginResponse {
        private String message;
        private String redirectUrl;

        public LoginResponse(String message, String redirectUrl) {
            this.message = message;
            this.redirectUrl = redirectUrl;
        }

        public String getMessage() {
            return message;
        }

        public String getRedirectUrl() {
            return redirectUrl;
        }
    }
}
