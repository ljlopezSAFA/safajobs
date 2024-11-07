package com.sl.safajobs.modelos;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "oferta_empleo", schema = "safajobs", catalog = "postgres")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OfertaEmpleo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "titular")
    private String titular;

    @Column(name = "puesto")
    private String puesto;

    @Column(name = "requisitos")
    private String requisitos;

    @Column(name = "remuneracion")
    private Double remuneracion;

    @Column(name = "remuneracion_minima")
    private Double remuneracionMinima;

    @Column(name = "remuneracion_maxima")
    private Double RemuneracionMaxima;

    @ManyToOne
    @JoinColumn(name = "id_empresa")
    private Empresa  empresa;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, targetEntity = Perfil.class)
    @JoinTable(name = "inscripcion_oferta_empleo", schema = "safajobs", catalog = "postgres",
            joinColumns = {@JoinColumn(name = "id_oferta_empleo", nullable = false)} ,
            inverseJoinColumns ={@JoinColumn(name = "id_perfil", nullable = false)})
    private Set<Perfil> inscritos;



}
