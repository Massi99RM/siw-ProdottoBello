package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.User;
import it.uniroma3.siw.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    
    // Metodi per l'autenticazione
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    
    // Verifica esistenza per validazione
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByUsernameOrEmail(String username, String email);
    
    // Ricerca per ruolo
    List<User> findByRole(UserRole role);
    List<User> findByRoleOrderByCreatedAtDesc(UserRole role);
    
    // Ricerca utenti attivi
    List<User> findByEnabledTrue();
    List<User> findByEnabledFalse();
    
    // Statistiche utenti
    long countByRole(UserRole role);
    long countByEnabledTrue();
    
    // Ricerca per nome/cognome
    List<User> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
    
    // Utenti registrati in un periodo
    @Query("SELECT u FROM User u WHERE u.createdAt BETWEEN :startDate AND :endDate ORDER BY u.createdAt DESC")
    List<User> findByCreatedAtBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
    
    // Aggiorna ultimo accesso
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.lastLogin = :loginTime WHERE u.id = :userId")
    void updateLastLogin(@Param("userId") Long userId, @Param("loginTime") LocalDateTime loginTime);
    
    // Disabilita utente
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.enabled = false WHERE u.id = :userId")
    void disableUser(@Param("userId") Long userId);
    
    // Abilita utente
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.enabled = true WHERE u.id = :userId")
    void enableUser(@Param("userId") Long userId);
}