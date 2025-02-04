package com.sl.safajobs.repositorios;

import com.sl.safajobs.dto.ChaDBDTO;
import com.sl.safajobs.modelos.Mensaje;
import com.sl.safajobs.modelos.Perfil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje,Integer> {


    @Query(value = "select distinct(u.id) from safajobs.mensajes m " +
            "join safajobs.perfil u on (m.id_emisor = u.id and m.id_receptor = :id ) " +
            "or (m.id_receptor  = u.id  and m.id_emisor = :id ) " ,
            nativeQuery = true)
    List<Integer> getConversacionesActivas(Integer id);


    @Query(value = "select m from Mensaje m " +
            "join Perfil u on (m.emisor.id = :idPerfil2 and m.receptor.id = :idPerfil1 ) or " +
            " (m.receptor.id  = :idPerfil2  and m.emisor.id = :idPerfil1 ) order by m.fecha desc " )
    Mensaje getUltimoMensaje(Integer idPerfil1, Integer idPerfil2);



    @Query(value = "SELECT * FROM ( " +
            "    SELECT DISTINCT ON (contacto) " +
            "        CASE " +
            "            WHEN id_emisor = :idPerfil THEN id_receptor " +
            "            ELSE id_emisor " +
            "        END AS contacto, " +
            "        fecha, " +
            "        mensaje " +
            "    FROM safajobs.mensajes " +
            "    WHERE id_emisor = :idPerfil OR id_receptor = :idPerfil " +
            "    ORDER BY contacto, fecha DESC " +
            ") sub " +
            "ORDER BY fecha desc" , nativeQuery = true)
    List<Object> getConversaciones(Integer idPerfil);


    @Query("select m from Mensaje m where (m.emisor.id = :idPerfil1 and m.receptor.id = :idPerfil2) or " +
            "(m.emisor.id = :idPerfil2 and m.receptor.id = :idPerfil1) order by m.fecha asc")
    List<Mensaje> getByEmisorYReceptor(Integer idPerfil1, Integer idPerfil2);



}
