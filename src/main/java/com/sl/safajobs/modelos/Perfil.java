package com.sl.safajobs.modelos;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "perfil", schema = "safajobs")
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
    @NotBlank(message = "No se puede crear un perfil sin nombre")
    private String nombre;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @Column(name = "puesto")
    private String puesto;

    @Column(name = "mail")
    private String mail;

    @Column(name = "dni")
    private String dni;

    @Column(name = "sobre_mi")
    private String sobreMi;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "imagen_url")
    private String foto;


    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, targetEntity = Aptitud.class)
    @JoinTable(name = "aptitud_perfil", schema = "safajobs",
            joinColumns = {@JoinColumn(name = "id_perfil", nullable = false)} ,
            inverseJoinColumns ={@JoinColumn(name = "id_aptitud", nullable = false)})
    private Set<Aptitud> aptitudes = new HashSet<>(0);


    @OneToMany(targetEntity = Publicacion.class, mappedBy = "perfil")
    private Set<Publicacion> publicacion;

    @OneToMany(targetEntity = ExperienciaEducativa.class, mappedBy = "perfil", cascade = CascadeType.ALL)
    private Set<ExperienciaEducativa> experienciaEducativa;


    @OneToMany(targetEntity = ExperienciaLaboral.class, mappedBy = "perfil", cascade = CascadeType.ALL)
    private Set<ExperienciaLaboral> experienciaLaboral;


    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
