package com.sl.safajobs.repositorios;

import com.sl.safajobs.modelos.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {


    //JPA Interface  select * from empresa where cif = "" and nombre like '% %';
    List<Empresa> findAllByCifEqualsAndNombreLike(String cif, String nombre);




}
