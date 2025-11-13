package com.miuniversidad.mi_universidad_web.Repository;
// Asegúrate de que el paquete sea el correcto

import com.miuniversidad.mi_universidad_web.Model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByUsername(String username);
}