package com.miuniversidad.mi_universidad_web.Service;

import com.miuniversidad.mi_universidad_web.Model.Profesor;
import com.miuniversidad.mi_universidad_web.Repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfesorService {

    private final ProfesorRepository profesorRepository;

    @Autowired
    public ProfesorService(ProfesorRepository profesorRepository) {
        this.profesorRepository = profesorRepository;
    }

    public Profesor guardarProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public Optional<Profesor> obtenerProfesorPorId(Long id) {
        return profesorRepository.findById(id);
    }

    public List<Profesor> obtenerTodosLosProfesores() {
        return profesorRepository.findAll();
    }

    public void eliminarProfesor(Long id) {
        profesorRepository.deleteById(id);
    }

    public Profesor encontrarPorEmail(String email) {
        return profesorRepository.findByEmail(email);
    }
}