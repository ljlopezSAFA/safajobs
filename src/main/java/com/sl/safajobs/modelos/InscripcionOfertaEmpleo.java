package com.sl.safajobs.modelos;

import com.sl.safajobs.enumerados.Estado;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "inscripcion_oferta_empleo", schema = "safajobs")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class InscripcionOfertaEmpleo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fecha_inscripcion")
    private LocalDate fechaIncripcion;

    @Column(name = "estado")
    @Enumerated(EnumType.ORDINAL)
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "id_perfil")
    private Perfil perfil;

    @ManyToOne
    @JoinColumn(name = "id_oferta_empleo")
    private OfertaEmpleo ofertaEmpleo;


}
