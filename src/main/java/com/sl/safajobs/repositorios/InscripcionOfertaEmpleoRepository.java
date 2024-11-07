package com.sl.safajobs.repositorios;

import com.sl.safajobs.modelos.InscripcionOfertaEmpleo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscripcionOfertaEmpleoRepository extends JpaRepository<InscripcionOfertaEmpleo, Integer> {

    @Query("select concat(i.perfil.nombre,',',i.perfil.apellidos) from InscripcionOfertaEmpleo i " +
            "where i.ofertaEmpleo.id = :id ")
    List<String> getInscritos(@Param("id") Integer idOfertaEmpleo);


    @Query("select count(distinct i.perfil.id) from InscripcionOfertaEmpleo i " +
            "where i.ofertaEmpleo.id = :id ")
    Integer getNumeroInscritos(@Param("id") Integer idOfertaEmpleo);



    Optional<InscripcionOfertaEmpleo> findByPerfilIdAndOfertaEmpleoId(Integer idPerfil, Integer idOfertaEmpleo);

}
