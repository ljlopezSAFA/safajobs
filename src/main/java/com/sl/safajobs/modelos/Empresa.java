package com.sl.safajobs.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;


@Entity
@Table(name = "empresa", schema = "safajobs")
@Getter
@Setter
@ToString(exclude = {"ofertasEmpleo"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "mail")
    private String mail;

    @Column(name = "cif")
    private String cif;

    @Column(name = "fecha_fundacion")
    private LocalDate fechaFundacion;

    @Column(name = "es_tecnologica")
    private Boolean esTecnologica;

    @Column(name = "imagen_url")
    private String foto;

    @OneToMany(targetEntity = OfertaEmpleo.class,
            mappedBy = "empresa",fetch = FetchType.LAZY)
    private Set<OfertaEmpleo> ofertasEmpleo;


}
