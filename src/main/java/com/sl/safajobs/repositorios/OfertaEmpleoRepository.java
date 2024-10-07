package com.sl.safajobs.repositorios;

import com.sl.safajobs.modelos.OfertaEmpleo;
import com.sl.safajobs.modelos.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfertaEmpleoRepository extends JpaRepository<OfertaEmpleo, Integer> {



}
