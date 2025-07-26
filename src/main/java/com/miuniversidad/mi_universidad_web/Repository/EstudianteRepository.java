package com.miuniversidad.mi_universidad_web.Repository;

import com.miuniversidad.mi_universidad_web.Model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Indica que esta interfaz es un componente de repositorio de Spring
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    // Spring Data JPA automáticamente proporciona métodos CRUD básicos
    // como save(), findById(), findAll(), deleteById(), etc.

    // Puedes añadir métodos personalizados aquí, por ejemplo:
    Estudiante findByEmail(String email); // Encuentra un estudiante por su email
}