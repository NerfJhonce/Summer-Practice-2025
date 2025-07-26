package com.miuniversidad.mi_universidad_web.Controller; // Asegúrate que coincida con tu carpeta

import com.miuniversidad.mi_universidad_web.Model.Curso; // Asegúrate que coincida con tu carpeta
import com.miuniversidad.mi_universidad_web.Service.CursoService; // Asegúrate que coincida con tu carpeta
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/cursos")
public class AdminCursoController {

    private final CursoService cursoService;

    @Autowired
    public AdminCursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public String listarCursos(Model model) {
        model.addAttribute("cursos", cursoService.obtenerTodosLosCursos());
        return "admin/cursos/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoCurso(Model model) {
        model.addAttribute("curso", new Curso());
        model.addAttribute("titulo", "Añadir Nuevo Curso/Programa");
        return "admin/cursos/formulario";
    }

    @PostMapping
    public String guardarCurso(@ModelAttribute Curso curso, RedirectAttributes redirectAttributes) {
        cursoService.guardarCurso(curso);
        redirectAttributes.addFlashAttribute("mensaje", "Curso/Programa guardado exitosamente!");
        return "redirect:/admin/cursos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCurso(@PathVariable Long id, Model model) {
        cursoService.obtenerCursoPorId(id).ifPresent(curso -> {
            model.addAttribute("curso", curso);
            model.addAttribute("titulo", "Editar Curso/Programa");
        });
        return "admin/cursos/formulario";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarCurso(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        cursoService.eliminarCurso(id);
        redirectAttributes.addFlashAttribute("mensaje", "Curso/Programa eliminado exitosamente!");
        return "redirect:/admin/cursos";
    }
}