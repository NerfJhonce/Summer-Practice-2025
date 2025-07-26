package com.miuniversidad.mi_universidad_web.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "estudiantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Estudiante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(unique = true, nullable = false)
    private String email;

    // CAMBIO IMPORTANTE AQUÍ: Relación Many-to-One con Curso
    @ManyToOne(fetch = FetchType.LAZY) // Muchos estudiantes pueden estar en un Curso
    @JoinColumn(name = "curso_id") // Columna en la tabla 'estudiantes' que guarda el ID del curso
    private Curso carrera; // Ahora 'carrera' es un objeto Curso

    // Getter y Setters serán manejados por Lombok con @Data
}