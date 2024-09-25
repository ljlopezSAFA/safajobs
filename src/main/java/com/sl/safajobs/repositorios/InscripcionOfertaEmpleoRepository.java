package com.sl.safajobs.repositorios;

import com.sl.safajobs.modelos.InscripcionOfertaEmpleo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionOfertaEmpleoRepository extends JpaRepository<InscripcionOfertaEmpleo, Integer> {
}
