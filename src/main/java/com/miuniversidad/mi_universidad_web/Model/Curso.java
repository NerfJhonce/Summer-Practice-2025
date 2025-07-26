package com.miuniversidad.mi_universidad_web.Model; // Asegúrate que coincida con tu carpeta

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cursos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre; // Ej: "Ingeniería Informática", "Diseño Gráfico"

    private String descripcion;
    private int creditos; // Ej: 240 (para un programa completo) o 6 (para un curso individual)

    // Aquí podrías añadir relaciones si quisieras, por ejemplo,
    // @ManyToOne o @OneToMany con Profesor o Estudiante, pero lo mantendremos simple por ahora.
}