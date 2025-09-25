package it.uniroma3.siw.controller;

import it.uniroma3.siw.model.Product;
import it.uniroma3.siw.model.ProductType;
import it.uniroma3.siw.model.User;
import it.uniroma3.siw.service.UserService;
import it.uniroma3.siw.service.ProductService;
import it.uniroma3.siw.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    private final UserService userService;
    private final ProductRepository productRepository;
    private final ProductService productService;

    public AdminController(UserService userService, ProductRepository productRepository, ProductService productService) {
        this.userService = userService;
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @GetMapping("/dashboard")
    public String adminDashboard(Model model) {
        long totalUsers = userService.countUsers();
        long totalAdmins = userService.countAdmins();
        long activeUsers = userService.countActiveUsers();
        long totalProducts = productRepository.count();

        model.addAttribute("totalUsers", totalUsers);
        model.addAttribute("totalAdmins", totalAdmins);
        model.addAttribute("activeUsers", activeUsers);
        model.addAttribute("totalProducts", totalProducts);

        return "admin/dashboard";
    }

    @GetMapping("/users")
    public String manageUsers(Model model) {
        List<User> allUsers = userService.findAllUsers();
        List<User> allAdmins = userService.findAllAdmins();

        model.addAttribute("users", allUsers);
        model.addAttribute("admins", allAdmins);

        return "admin/manageUsers";
    }

    @GetMapping("")
    public String adminHome() {
        return "redirect:/admin/dashboard";
    }

    // ===== GESTIONE PRODOTTI =====

    @GetMapping("/products")
    public String manageProducts(Model model) {
        List<Product> products = productRepository.findAll();
        Map<ProductType, List<Product>> productsByCategory = productService.getProductsByCategory();

        model.addAttribute("products", products);
        model.addAttribute("productTypes", ProductType.values());
        model.addAttribute("product", new Product());
        model.addAttribute("allProducts", products);
        model.addAttribute("productsByCategory", productsByCategory);

        return "admin/manageProducts";
    }

    @GetMapping("/products/new")
    public String newProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("productTypes", ProductType.values());
        model.addAttribute("allProducts", productRepository.findAll());
        model.addAttribute("productsByCategory", productService.getProductsByCategory());

        return "admin/manageProducts";
    }

    @PostMapping("/products/save")
    public String saveProduct(@Valid @ModelAttribute Product product,
                              BindingResult result,
                              @RequestParam(required = false) List<Long> similarProductIds,
                              RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Errori nella validazione del prodotto");
            return "redirect:/admin/products";
        }

        try {
            productService.saveProductWithSimilarProducts(product, similarProductIds);
            redirectAttributes.addFlashAttribute("successMessage", "Prodotto salvato con successo!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Errore nel salvare il prodotto: " + e.getMessage());
        }

        return "redirect:/admin/products";
    }

    @GetMapping("/products/edit/{id}")
    public String editProductForm(@PathVariable Long id, Model model) {
        Optional<Product> productOpt = productRepository.findById(id);

        if (productOpt.isPresent()) {
            model.addAttribute("product", productOpt.get());
            model.addAttribute("productTypes", ProductType.values());
            model.addAttribute("allProducts", productRepository.findAll());
            model.addAttribute("productsByCategory", productService.getProductsByCategoryExcluding(id));
            model.addAttribute("isEdit", true);
        } else {
            return "redirect:/admin/products";
        }

        return "admin/manageProducts";
    }

    @PostMapping("/products/update/{id}")
    public String updateProduct(@PathVariable Long id,
                                @Valid @ModelAttribute Product product,
                                BindingResult result,
                                @RequestParam(required = false) List<Long> similarProductIds,
                                RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Errori nella validazione del prodotto");
            return "redirect:/admin/products";
        }

        try {
            productService.updateProductWithSimilarProducts(id, product, similarProductIds);
            redirectAttributes.addFlashAttribute("successMessage", "Prodotto aggiornato con successo!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Errore nell'aggiornare il prodotto: " + e.getMessage());
        }

        return "redirect:/admin/products";
    }

    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            productService.deleteProduct(id);
            redirectAttributes.addFlashAttribute("successMessage", "Prodotto eliminato con successo!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage",
                    "Errore nell'eliminare il prodotto: " + e.getMessage());
        }

        return "redirect:/admin/products";
    }
}
