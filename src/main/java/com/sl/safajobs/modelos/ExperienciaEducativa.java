package com.sl.safajobs.modelos;

import com.sl.safajobs.enumerados.TipoAptitud;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "experiencia_educativa", schema = "safajobs", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ExperienciaEducativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "centro_educativo", nullable = false)
    private String centroEducativo;

    @Column(name = "curso", nullable = false)
    private String curso;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

    @ManyToOne
    @JoinColumn(name = "id_perfil")
    private Perfil perfil;

}
