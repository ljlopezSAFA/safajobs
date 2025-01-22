package com.sl.safajobs.repositorios;


import com.sl.safajobs.modelos.Perfil;
import com.sl.safajobs.modelos.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicacionRepository extends JpaRepository<Publicacion,Integer> {

    List<Publicacion> findByPerfil(Perfil perfil);

}
