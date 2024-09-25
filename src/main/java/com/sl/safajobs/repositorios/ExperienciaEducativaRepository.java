package com.sl.safajobs.repositorios;

import com.sl.safajobs.modelos.ExperienciaEducativa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExperienciaEducativaRepository extends JpaRepository<ExperienciaEducativa, Integer> {
}
