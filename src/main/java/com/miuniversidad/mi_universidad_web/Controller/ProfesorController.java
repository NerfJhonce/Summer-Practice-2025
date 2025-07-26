package com.miuniversidad.mi_universidad_web.Controller; // Asegúrate que coincida con tu carpeta

import com.miuniversidad.mi_universidad_web.Model.Profesor; // Asegúrate que coincida con tu carpeta
import com.miuniversidad.mi_universidad_web.Service.ProfesorService; // Asegúrate que coincida con tu carpeta
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profesores")
public class ProfesorController {

    private final ProfesorService profesorService;

    @Autowired
    public ProfesorController(ProfesorService profesorService) {
        this.profesorService = profesorService;
    }

    @PostMapping
    public ResponseEntity<Profesor> crearProfesor(@RequestBody Profesor profesor) {
        Profesor nuevoProfesor = profesorService.guardarProfesor(profesor);
        return new ResponseEntity<>(nuevoProfesor, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Profesor>> obtenerTodosLosProfesores() {
        List<Profesor> profesores = profesorService.obtenerTodosLosProfesores();
        return new ResponseEntity<>(profesores, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profesor> obtenerProfesorPorId(@PathVariable Long id) {
        Optional<Profesor> profesor = profesorService.obtenerProfesorPorId(id);
        return profesor.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profesor> actualizarProfesor(@PathVariable Long id, @RequestBody Profesor profesorDetalles) {
        Optional<Profesor> profesorExistente = profesorService.obtenerProfesorPorId(id);
        if (profesorExistente.isPresent()) {
            Profesor profesor = profesorExistente.get();
            profesor.setNombre(profesorDetalles.getNombre());
            profesor.setApellido(profesorDetalles.getApellido());
            profesor.setEmail(profesorDetalles.getEmail());
            profesor.setDepartamento(profesorDetalles.getDepartamento());
            Profesor profesorActualizado = profesorService.guardarProfesor(profesor);
            return new ResponseEntity<>(profesorActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarProfesor(@PathVariable Long id) {
        try {
            profesorService.eliminarProfesor(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}