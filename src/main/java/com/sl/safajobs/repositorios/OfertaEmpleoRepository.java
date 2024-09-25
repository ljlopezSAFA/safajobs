package com.sl.safajobs.repositorios;

import com.sl.safajobs.modelos.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfertaEmpleoRepository extends JpaRepository<Perfil, Integer> {
}
