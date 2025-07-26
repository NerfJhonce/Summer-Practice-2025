package com.miuniversidad.mi_universidad_web.Service; // CAMBIADO a 'Service' con 'S' mayúscula

import com.miuniversidad.mi_universidad_web.Model.Estudiante; // IMPORT CAMBIADO a 'Model' con 'M' mayúscula
import com.miuniversidad.mi_universidad_web.Repository.EstudianteRepository; // IMPORT CAMBIADO a 'Repository' con 'R' mayúscula
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    @Autowired
    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public Estudiante guardarEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public Optional<Estudiante> obtenerEstudiantePorId(Long id) {
        return estudianteRepository.findById(id);
    }

    public List<Estudiante> obtenerTodosLosEstudiantes() {
        return estudianteRepository.findAll();
    }

    public void eliminarEstudiante(Long id) {
        estudianteRepository.deleteById(id);
    }

    public Estudiante encontrarPorEmail(String email) {
        return estudianteRepository.findByEmail(email);
    }
}