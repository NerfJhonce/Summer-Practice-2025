package com.miuniversidad.mi_universidad_web.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "profesores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(unique = true, nullable = false)
    private String email;

    // CAMBIO IMPORTANTE AQUÍ: Relación Many-to-One con Curso para "departamento"
    @ManyToOne(fetch = FetchType.LAZY) // Muchos profesores pueden pertenecer a un Curso/Programa
    @JoinColumn(name = "departamento_id") // Columna en la tabla 'profesores' que guarda el ID del curso
    private Curso departamento; // Ahora 'departamento' es un objeto Curso

    // Getter y Setters serán manejados por Lombok con @Data
}