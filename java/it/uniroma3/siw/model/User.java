package it.uniroma3.siw.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Username è obbligatorio")
    @Size(min = 3, max = 50, message = "Username deve essere tra 3 e 50 caratteri")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Email è obbligatoria")
    @Email(message = "Formato email non valido")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password è obbligatoria")
    @Size(min = 6, message = "Password deve essere almeno di 6 caratteri")
    private String password;

    @NotBlank(message = "Nome è obbligatorio")
    private String firstName;

    @NotBlank(message = "Cognome è obbligatorio")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    private boolean enabled = true;
    
    
    // Relazione con i commenti (sarà utile dopo)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();
	
    // Costruttori
    public User() {
        this.createdAt = LocalDateTime.now();
    }

    public User(String username, String email, String password, String firstName, String lastName) {
        this();
        this.username = username;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String username, String email, String password, String firstName, String lastName, UserRole role) {
        this(username, email, password, firstName, lastName);
        this.role = role;
    }

    // Metodi di utility
    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean isAdmin() {
        return role == UserRole.ADMIN;
    }

    public boolean isUser() {
        return role == UserRole.USER;
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public UserRole getRole() { return role; }
    public void setRole(UserRole role) { this.role = role; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getLastLogin() { return lastLogin; }
    public void setLastLogin(LocalDateTime lastLogin) { this.lastLogin = lastLogin; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

   public Set<Comment> getComments() { return comments; }
   public void setComments(Set<Comment> comments) { this.comments = comments; }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', email='" + email + "', role=" + role + "}";
    }
}