package com.sl.safajobs.repositorios;

import com.sl.safajobs.modelos.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {


    @Query("SELECT p FROM Perfil p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :busqueda, '%')) " +
            "OR LOWER(p.apellidos) LIKE LOWER(CONCAT('%', :busqueda, '%')) " +
            "OR LOWER(p.mail) LIKE LOWER(CONCAT('%', :busqueda, '%'))")
    List<Perfil> buscar(@Param("busqueda") String busqueda);


    @Query(value = "select count(*) from safajobs.empresa e  where es_tecnologica = false", nativeQuery = true )
    Integer getNumeroEmpresasNoTecnologicas();


}
