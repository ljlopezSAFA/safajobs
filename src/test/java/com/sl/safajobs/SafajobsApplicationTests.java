package com.sl.safajobs;

import com.sl.safajobs.modelos.Aptitud;
import com.sl.safajobs.repositorios.AptitudRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SafajobsApplicationTests {

    @Autowired
    private AptitudRepository aptitudRepository;

    @Test
    void testFindAllAptitudes() {

        List<Aptitud> aptituds = aptitudRepository.findAll();
        for(Aptitud a :aptituds){
            System.out.println(a.getTitulo());
        }

    }

}
