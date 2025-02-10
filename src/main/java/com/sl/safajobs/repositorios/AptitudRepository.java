package com.sl.safajobs.repositorios;

import com.sl.safajobs.modelos.Aptitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AptitudRepository extends JpaRepository<Aptitud, Integer> {


}
