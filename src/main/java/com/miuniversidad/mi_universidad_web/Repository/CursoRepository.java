package com.miuniversidad.mi_universidad_web.Repository; // Asegúrate que coincida con tu carpeta

import com.miuniversidad.mi_universidad_web.Model.Curso; // Asegúrate que coincida con tu carpeta
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    Curso findByNombre(String nombre);
}