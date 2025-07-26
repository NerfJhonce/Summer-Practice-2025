package com.miuniversidad.mi_universidad_web.Service; // Asegúrate que coincida con tu carpeta

import com.miuniversidad.mi_universidad_web.Model.Curso; // Asegúrate que coincida con tu carpeta
import com.miuniversidad.mi_universidad_web.Repository.CursoRepository; // Asegúrate que coincida con tu carpeta
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso guardarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public Optional<Curso> obtenerCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    public List<Curso> obtenerTodosLosCursos() {
        return cursoRepository.findAll();
    }

    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }

    public Curso encontrarPorNombre(String nombre) {
        return cursoRepository.findByNombre(nombre);
    }
}