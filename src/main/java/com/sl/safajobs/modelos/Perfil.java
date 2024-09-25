package com.sl.safajobs.modelos;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "perfil", schema = "safajobs", catalog = "postgres")
@Getter
@Setter
@ToString(exclude = {"aptitudes"})
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"aptitudes"})
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "mail")
    private String mail;

    @Column(name = "dni")
    private String dni;

    @Column(name = "fecha_nacmiento")
    private LocalDate fechaNacimiento;


    @ManyToMany
    @JoinTable(name = "aptitud_perfil",
            joinColumns = {@JoinColumn(name = "id_perfil", nullable = false)} ,
            inverseJoinColumns ={@JoinColumn(name = "id_aptitud", nullable = false)})
    private Set<Aptitud> aptitudes;


}
