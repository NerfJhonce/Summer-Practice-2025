package com.miuniversidad.mi_universidad_web.Controller;

import com.miuniversidad.mi_universidad_web.Model.Profesor;
import com.miuniversidad.mi_universidad_web.Model.Curso; // Importar Curso
import com.miuniversidad.mi_universidad_web.Service.ProfesorService;
import com.miuniversidad.mi_universidad_web.Service.CursoService; // Importar CursoService
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/profesores")
public class AdminProfesorController {

    private final ProfesorService profesorService;
    private final CursoService cursoService; // Inyectar CursoService

    @Autowired
    public AdminProfesorController(ProfesorService profesorService, CursoService cursoService) {
        this.profesorService = profesorService;
        this.cursoService = cursoService; // Inicializar
    }

    @GetMapping
    public String listarProfesores(Model model) {
        model.addAttribute("profesores", profesorService.obtenerTodosLosProfesores());
        return "admin/profesores/lista";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoProfesor(Model model) {
        model.addAttribute("profesor", new Profesor());
        model.addAttribute("titulo", "Añadir Nuevo Profesor");
        model.addAttribute("cursos", cursoService.obtenerTodosLosCursos()); // Añadir lista de cursos
        return "admin/profesores/formulario";
    }

    @PostMapping
    public String guardarProfesor(@ModelAttribute Profesor profesor, RedirectAttributes redirectAttributes) {
        profesorService.guardarProfesor(profesor);
        redirectAttributes.addFlashAttribute("mensaje", "Profesor guardado exitosamente!");
        return "redirect:/admin/profesores";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarProfesor(@PathVariable Long id, Model model) {
        profesorService.obtenerProfesorPorId(id).ifPresent(profesor -> {
            model.addAttribute("profesor", profesor);
            model.addAttribute("titulo", "Editar Profesor");
            model.addAttribute("cursos", cursoService.obtenerTodosLosCursos()); // Añadir lista de cursos
        });
        return "admin/profesores/formulario";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarProfesor(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        profesorService.eliminarProfesor(id);
        redirectAttributes.addFlashAttribute("mensaje", "Profesor eliminado exitosamente!");
        return "redirect:/admin/profesores";
    }
}