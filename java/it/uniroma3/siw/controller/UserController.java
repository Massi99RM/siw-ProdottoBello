package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Comment;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.CustomUserDetailsService.CustomUserPrincipal;
import it.uniroma3.siw.service.CommentService;
import it.uniroma3.siw.service.UserService;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
	
	private final CommentService commentService;
    private final UserService userService;

    public UserController(CommentService commentService, UserService userService) {
    	this.commentService = commentService;
        this.userService = userService;
    }

    // Pagina di login
    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error,
                       @RequestParam(required = false) String logout,
                       Model model) {
        
        if (error != null) {
            model.addAttribute("errorMessage", "Username o password non corretti");
        }
        if (logout != null) {
            model.addAttribute("logoutMessage", "Logout effettuato con successo");
        }
        
        return "login";
    }

    // Pagina di registrazione
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Gestione registrazione POST
    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user,
                              BindingResult result,
                              @RequestParam String confirmPassword,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        
        // Validazione password
        if (!user.getPassword().equals(confirmPassword)) {
            result.rejectValue("password", "error.user", "Le password non corrispondono");
        }

        // Validazione username/email univoci
        if (!userService.isUsernameAvailable(user.getUsername())) {
            result.rejectValue("username", "error.user", "Username già in uso");
        }
        if (!userService.isEmailAvailable(user.getEmail())) {
            result.rejectValue("email", "error.user", "Email già in uso");
        }

        if (result.hasErrors()) {
            return "register";
        }

        try {
            userService.registerUser(
                user.getUsername(),
                user.getEmail(), 
                user.getPassword(),
                user.getFirstName(),
                user.getLastName()
            );

            redirectAttributes.addFlashAttribute("successMessage", 
                "Registrazione completata! Puoi ora effettuare il login.");
            return "redirect:/login";

        } catch (Exception e) {
            model.addAttribute("errorMessage", "Errore durante la registrazione: " + e.getMessage());
            return "register";
        }
    }

    // Pagina profilo utente
    @GetMapping("/profile")
    public String profile(Model model, Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        CustomUserPrincipal userPrincipal = (CustomUserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();
        model.addAttribute("user", user);
        
        // Carica i commenti dell'utente
        List<Comment> userComments = commentService.getCommentsByUser(user.getId());
        model.addAttribute("comments", userComments);
        
        return "profile";
    }

    // Aggiorna profilo utente
    @PostMapping("/profile/update")
    public String updateProfile(@RequestParam String firstName,
                               @RequestParam String lastName,
                               @RequestParam String email,
                               Authentication authentication,
                               RedirectAttributes redirectAttributes) {
        
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        try {
            CustomUserPrincipal userPrincipal = (CustomUserPrincipal) authentication.getPrincipal();
            userService.updateUserProfile(userPrincipal.getId(), firstName, lastName, email);
            
            redirectAttributes.addFlashAttribute("successMessage", "Profilo aggiornato con successo!");
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Errore: " + e.getMessage());
        }

        return "redirect:/profile";
    }

    // Cambia password
    @PostMapping("/profile/change-password")
    public String changePassword(@RequestParam String currentPassword,
                                @RequestParam String newPassword,
                                @RequestParam String confirmNewPassword,
                                Authentication authentication,
                                RedirectAttributes redirectAttributes) {
        
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        if (!newPassword.equals(confirmNewPassword)) {
            redirectAttributes.addFlashAttribute("passwordError", "Le nuove password non corrispondono");
            return "redirect:/profile";
        }

        if (newPassword.length() < 6) {
            redirectAttributes.addFlashAttribute("passwordError", "La password deve essere di almeno 6 caratteri");
            return "redirect:/profile";
        }

        try {
            CustomUserPrincipal userPrincipal = (CustomUserPrincipal) authentication.getPrincipal();
            userService.changePassword(userPrincipal.getId(), currentPassword, newPassword);
            
            redirectAttributes.addFlashAttribute("passwordSuccess", "Password cambiata con successo!");
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("passwordError", "Errore: " + e.getMessage());
        }

        return "redirect:/profile";
    }

    // Pagina accesso negato
    @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied";
    }

    // Verifica disponibilità username (AJAX)
    @GetMapping("/api/check-username")
    @ResponseBody
    public boolean checkUsername(@RequestParam String username) {
        return userService.isUsernameAvailable(username);
    }

    // Verifica disponibilità email (AJAX)
    @GetMapping("/api/check-email")
    @ResponseBody
    public boolean checkEmail(@RequestParam String email) {
        return userService.isEmailAvailable(email);
    }
}