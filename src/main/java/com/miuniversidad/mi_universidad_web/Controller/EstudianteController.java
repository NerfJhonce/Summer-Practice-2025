package com.miuniversidad.mi_universidad_web.Controller; // Asegúrate de que coincida con tu carpeta (Controller o controller)

import com.miuniversidad.mi_universidad_web.Model.Estudiante; // Asegúrate de que coincida con tu carpeta (Model o model)
import com.miuniversidad.mi_universidad_web.Service.EstudianteService; // Asegúrate de que coincida con tu carpeta (Service o service)
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Indica que esta clase es un controlador REST (responde con JSON/XML)
@RequestMapping("/api/estudiantes") // Define el prefijo de la URL para todos los endpoints de este controlador
public class EstudianteController {

    private final EstudianteService estudianteService;

    @Autowired
    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @PostMapping
    public ResponseEntity<Estudiante> crearEstudiante(@RequestBody Estudiante estudiante) {
        Estudiante nuevoEstudiante = estudianteService.guardarEstudiante(estudiante);
        return new ResponseEntity<>(nuevoEstudiante, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Estudiante>> obtenerTodosLosEstudiantes() {
        List<Estudiante> estudiantes = estudianteService.obtenerTodosLosEstudiantes();
        return new ResponseEntity<>(estudiantes, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Estudiante> obtenerEstudiantePorId(@PathVariable Long id) {
        Optional<Estudiante> estudiante = estudianteService.obtenerEstudiantePorId(id);
        return estudiante.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Estudiante> actualizarEstudiante(@PathVariable Long id, @RequestBody Estudiante estudianteDetalles) {
        Optional<Estudiante> estudianteExistente = estudianteService.obtenerEstudiantePorId(id);
        if (estudianteExistente.isPresent()) {
            Estudiante estudiante = estudianteExistente.get();
            estudiante.setNombre(estudianteDetalles.getNombre());
            estudiante.setApellido(estudianteDetalles.getApellido());
            estudiante.setEmail(estudianteDetalles.getEmail());
            estudiante.setCarrera(estudianteDetalles.getCarrera());
            Estudiante estudianteActualizado = estudianteService.guardarEstudiante(estudiante);
            return new ResponseEntity<>(estudianteActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarEstudiante(@PathVariable Long id) {
        try {
            estudianteService.eliminarEstudiante(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // O NOT_FOUND si la eliminación falla por ID inexistente
        }
    }
}