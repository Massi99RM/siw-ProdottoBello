package it.uniroma3.siw.service;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.model.UserRole;
import it.uniroma3.siw.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Registrazione nuovo utente
    public User registerUser(String username, String email, String password, String firstName, String lastName) {
        if (userRepository.existsByUsernameOrEmail(username, email)) {
            throw new RuntimeException("Username o email già in uso");
        }

        User user = new User(username, email, passwordEncoder.encode(password), firstName, lastName);
        user.setRole(UserRole.USER);
        return userRepository.save(user);
    }

    // Registrazione admin (solo per setup iniziale)
    public User registerAdmin(String username, String email, String password, String firstName, String lastName) {
        if (userRepository.existsByUsernameOrEmail(username, email)) {
            throw new RuntimeException("Username o email già in uso");
        }

        User admin = new User(username, email, passwordEncoder.encode(password), firstName, lastName, UserRole.ADMIN);
        return userRepository.save(admin);
    }

    // Validazione per la registrazione
    public boolean isUsernameAvailable(String username) {
        return !userRepository.existsByUsername(username);
    }

    public boolean isEmailAvailable(String email) {
        return !userRepository.existsByEmail(email);
    }

    // Trova utenti
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAllUsers() {
        return userRepository.findByRole(UserRole.USER);
    }

    public List<User> findAllAdmins() {
        return userRepository.findByRole(UserRole.ADMIN);
    }

    public List<User> findActiveUsers() {
        return userRepository.findByEnabledTrue();
    }

    // Aggiorna profilo utente
    public User updateUserProfile(Long userId, String firstName, String lastName, String email) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        // Verifica se email è già usata da altri
        if (!user.getEmail().equals(email) && userRepository.existsByEmail(email)) {
            throw new RuntimeException("Email già in uso da un altro utente");
        }

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);

        return userRepository.save(user);
    }

    // Cambia password
    public void changePassword(Long userId, String currentPassword, String newPassword) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Utente non trovato"));

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new RuntimeException("Password attuale non corretta");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    // Gestione admin
    public void enableUser(Long userId) {
        userRepository.enableUser(userId);
    }

    public void disableUser(Long userId) {
        userRepository.disableUser(userId);
    }

    public User promoteToAdmin(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Utente non trovato"));
        
        user.setRole(UserRole.ADMIN);
        return userRepository.save(user);
    }

    public User demoteToUser(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("Utente non trovato"));
        
        user.setRole(UserRole.USER);
        return userRepository.save(user);
    }

    // Statistiche
    public long countUsers() {
        return userRepository.countByRole(UserRole.USER);
    }

    public long countAdmins() {
        return userRepository.countByRole(UserRole.ADMIN);
    }

    public long countActiveUsers() {
        return userRepository.countByEnabledTrue();
    }

    // Ricerca utenti
    public List<User> searchUsers(String searchTerm) {
        return userRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchTerm, searchTerm);
    }

    // Metodo per creare admin di default se non esiste
    @Transactional
    public void createDefaultAdminIfNotExists() {
        if (userRepository.countByRole(UserRole.ADMIN) == 0) {
            registerAdmin("admin", "admin@prodottobello.com", "admin123", "Admin", "System");
        }
    }
}