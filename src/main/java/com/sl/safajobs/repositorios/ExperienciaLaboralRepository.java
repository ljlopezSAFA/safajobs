package com.sl.safajobs.repositorios;

import com.sl.safajobs.modelos.ExperienciaLaboral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExperienciaLaboralRepository extends JpaRepository<ExperienciaLaboral, Integer> {



    List<ExperienciaLaboral> findAllByPerfilId(Integer id);



}
