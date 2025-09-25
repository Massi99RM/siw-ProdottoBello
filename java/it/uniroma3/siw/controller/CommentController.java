package it.uniroma3.siw.controller;

import it.uniroma3.siw.service.CommentService;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;
  

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
        
    }

    // Aggiunta commento
    @PostMapping("/add/{productId}")
    public String addComment(@PathVariable Long productId,
                             @RequestParam String content,
                             @AuthenticationPrincipal UserDetails userDetails) {

        // Controllo utente non autenticato
        if (userDetails == null) {
            return "redirect:/login";
        }

        commentService.addComment(content, productId, userDetails.getUsername());
        return "redirect:/product/" + productId;
    }

    // Modifica commento
    @PostMapping("/update/{commentId}")
    public String updateComment(@PathVariable Long commentId,
                                @RequestParam String content,
                                @RequestParam Long productId,
                                @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails == null) {
            return "redirect:/login";
        }

        commentService.updateComment(commentId, content, userDetails.getUsername());
        return "redirect:/product/" + productId;
    }

    // Eliminazione commento
    @PostMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable Long commentId,
                                @RequestParam Long productId,
                                @AuthenticationPrincipal UserDetails userDetails) {

        if (userDetails == null) {
            return "redirect:/login";
        }

        commentService.deleteComment(commentId, userDetails.getUsername());
        return "redirect:/product/" + productId;
    }
}
