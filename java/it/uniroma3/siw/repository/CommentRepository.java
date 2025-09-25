package it.uniroma3.siw.repository;

import it.uniroma3.siw.model.Comment;
import it.uniroma3.siw.model.Product;
import it.uniroma3.siw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    // Trova tutti i commenti di un certo prodotto
    List<Comment> findByProduct(Product product);

    // Trova tutti i commenti scritti da un certo utente
    List<Comment> findByUser(User user);

    // Trova un commento specifico di un utente su un prodotto (utile per controllo permessi)
    Comment findByIdAndUser(Long id, User user);
}