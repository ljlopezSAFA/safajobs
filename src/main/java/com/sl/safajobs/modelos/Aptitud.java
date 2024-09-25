package com.sl.safajobs.modelos;


import com.sl.safajobs.enumerados.TipoAptitud;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "aptitud", catalog = "postgres", schema = "safajobs")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Aptitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "tipo")
    @Enumerated(EnumType.ORDINAL)
    private TipoAptitud tipoAptitud;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "detalle")
    private String detalle;


}
