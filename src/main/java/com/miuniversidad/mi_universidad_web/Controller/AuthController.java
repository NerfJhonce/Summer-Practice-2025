package com.miuniversidad.mi_universidad_web.Controller;

import com.miuniversidad.mi_universidad_web.Service.UsuarioService;
import com.miuniversidad.mi_universidad_web.Model.Usuario;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // --- Lógica de Registro (GET) ---
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registration";
    }

    // --- Lógica de Registro (POST) ---
    @PostMapping("/register")
    public String registerUser(@ModelAttribute Usuario usuario) {

        // 1. Validar si el usuario ya existe
        try {
            // Intenta cargar el usuario. Si lo encuentra, lanza el error.
            usuarioService.loadUserByUsername(usuario.getUsername());

            // Si llegamos aquí, el usuario existe -> redirigimos con error
            return "redirect:/register?error=user_exists";

        } catch (UsernameNotFoundException e) {
            // 2. El usuario NO existe -> procedemos con el registro
            usuarioService.registerNewUser(usuario);
            return "redirect:/login?success";
        }
    }

    // --- Lógica de Login (GET) ---
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}