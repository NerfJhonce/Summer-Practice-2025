package com.miuniversidad.mi_universidad_web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PublicController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/acerca-de")
    public String acercaDe() {
        return "acerca-de";
    }

    @GetMapping("/programas-estudio")
    public String programasEstudio() {
        return "programas-estudio";
    }

    @GetMapping("/contacto")
    public String contacto() {
        return "contacto";
    }
}