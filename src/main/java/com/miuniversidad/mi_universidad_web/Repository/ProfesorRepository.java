package com.miuniversidad.mi_universidad_web.Repository;

import com.miuniversidad.mi_universidad_web.Model.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    Profesor findByEmail(String email);
}