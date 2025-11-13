package com.miuniversidad.mi_universidad_web.Repository;

import com.miuniversidad.mi_universidad_web.Model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    // Spring Data JPA automáticamente proporciona métodos CRUD básicos
    Estudiante findByEmail(String email);
}