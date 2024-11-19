package com.sl.safajobs.modelos;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "mensajes", catalog = "postgres", schema = "safajobs")
@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "mensaje")
    private String mensaje ;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @ManyToOne(targetEntity = Perfil.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_emisor")
    private Perfil emisor;

    @ManyToOne(targetEntity = Perfil.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_receptor")
    private Perfil receptor;



}
