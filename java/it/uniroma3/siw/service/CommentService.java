package it.uniroma3.siw.service;

import it.uniroma3.siw.model.Comment;
import it.uniroma3.siw.model.Product;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.repository.CommentRepository;
import it.uniroma3.siw.repository.ProductRepository;
import it.uniroma3.siw.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;

	public CommentService(CommentRepository commentRepository,
			ProductRepository productRepository,
			UserRepository userRepository) {
		this.commentRepository = commentRepository;
		this.productRepository = productRepository;
		this.userRepository = userRepository;
	}

	// Lista dei commenti di un prodotto
	public List<Comment> getCommentsByProduct(Long productId) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato"));
		return commentRepository.findByProduct(product);
	}

	// Lista dei commenti di un utente
	public List<Comment> getCommentsByUser(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));
		return commentRepository.findByUser(user);
	}

	// Creazione di un nuovo commento
	public void addComment(String content, Long productId, String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("Utente non trovato"));

		if (user.isAdmin()) {
			throw new RuntimeException("Gli amministratori non possono aggiungere commenti");
		}

		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Prodotto non trovato"));

		Comment comment = new Comment(content, user, product);
		commentRepository.save(comment);
	}


	// Aggiornamento commento (solo autore)
	public Optional<Comment> updateComment(Long commentId, String newContent, String username) {
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new IllegalArgumentException("Commento non trovato"));

		if (!comment.getUser().getUsername().equals(username)) {
			return Optional.empty(); // non autorizzato
		}

		comment.setContent(newContent);
		return Optional.of(commentRepository.save(comment));
	}

	// Eliminazione commento (solo autore)
	public boolean deleteComment(Long commentId, String username) {
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new IllegalArgumentException("Commento non trovato"));

		if (!comment.getUser().getUsername().equals(username)) {
			return false; // non autorizzato
		}

		commentRepository.delete(comment);
		return true;
	}
}
