package com.miuniversidad.mi_universidad_web.Repository; // Asegúrate que coincida con tu carpeta

import com.miuniversidad.mi_universidad_web.Model.Profesor; // Asegúrate que coincida con tu carpeta
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    Profesor findByEmail(String email);
}