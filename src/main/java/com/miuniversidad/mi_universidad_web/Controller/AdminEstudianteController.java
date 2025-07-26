package com.miuniversidad.mi_universidad_web.Controller;

import com.miuniversidad.mi_universidad_web.Model.Estudiante;
import com.miuniversidad.mi_universidad_web.Model.Curso;
import com.miuniversidad.mi_universidad_web.Service.EstudianteService;
import com.miuniversidad.mi_universidad_web.Service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin/estudiantes")
public class AdminEstudianteController {

    private final EstudianteService estudianteService;
    private final CursoService cursoService; // Inyectar CursoService

    @Autowired
    public AdminEstudianteController(EstudianteService estudianteService, CursoService cursoService) {
        this.estudianteService = estudianteService;
        this.cursoService = cursoService; // Inicializar
    }

    // LIST STUDENTS
    @GetMapping
    public String listarEstudiantes(Model model) {
        model.addAttribute("estudiantes", estudianteService.obtenerTodosLosEstudiantes());
        return "admin/estudiantes/lista";
    }

    // MOSTRAR FORMULARIO PARA NUEVO ESTUDIANTE
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoEstudiante(Model model) {
        model.addAttribute("estudiante", new Estudiante());
        model.addAttribute("titulo", "Añadir Nuevo Estudiante");
        model.addAttribute("cursos", cursoService.obtenerTodosLosCursos());
        return "admin/estudiantes/formulario";
    }

    // GUARDAR ESTUDIANTE (NUEVO O EDITADO)
    @PostMapping
    public String guardarEstudiante(@ModelAttribute Estudiante estudiante, RedirectAttributes redirectAttributes) {
        estudianteService.guardarEstudiante(estudiante);
        redirectAttributes.addFlashAttribute("mensaje", "Estudiante guardado exitosamente!");
        return "redirect:/admin/estudiantes";
    }

    // MOSTRAR FORMULARIO PARA EDITAR ESTUDIANTE
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarEstudiante(@PathVariable Long id, Model model) {
        estudianteService.obtenerEstudiantePorId(id).ifPresent(estudiante -> {
            model.addAttribute("estudiante", estudiante);
            model.addAttribute("titulo", "Editar Estudiante");
            model.addAttribute("cursos", cursoService.obtenerTodosLosCursos()); // Here we add the list of courses
        });
        return "admin/estudiantes/formulario";
    }

    // ELIMINAR ESTUDIANTE (no cambia)
    @PostMapping("/eliminar/{id}")
    public String eliminarEstudiante(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        estudianteService.eliminarEstudiante(id);
        redirectAttributes.addFlashAttribute("mensaje", "Estudiante eliminado exitosamente!");
        return "redirect:/admin/estudiantes";
    }
}