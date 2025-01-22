package com.sl.safajobs.servicios;

import com.sl.safajobs.dto.RegistroDTO;
import com.sl.safajobs.enumerados.Rol;
import com.sl.safajobs.modelos.Perfil;
import com.sl.safajobs.modelos.Usuario;
import com.sl.safajobs.repositorios.UsuarioRepository;
import com.sl.safajobs.security.JWTService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;

@Service
@AllArgsConstructor
public class UsuarioService implements UserDetailsService {


    private UsuarioRepository usuarioRepository;
    private PerfilService perfilService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findTopByUsername(username).orElse(null);
    }


    public Usuario registrarUsuario(RegistroDTO dto){

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(dto.getUsername());
        nuevoUsuario.setPassword(passwordEncoder.encode(dto.getPassword()));
        nuevoUsuario.setRol(Rol.PERFIL);


        Perfil perfil = new Perfil();
        perfil.setNombre(dto.getNombre());
        perfil.setApellidos(dto.getApellidos());
        perfil.setDni(dto.getDni());
        perfil.setMail(dto.getMail());
        perfil.setPuesto("Sin Puesto");
        perfil.setAptitudes(new HashSet<>());
        perfil.setPublicacion(new HashSet<>());


        //FECHA NACIMIENTO (STRING) -> LOCALTADE
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNacimiento = LocalDate.parse(dto.getFechaNacimiento(), formatter);
        perfil.setFechaNacimiento(fechaNacimiento);

        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);


        perfil.setUsuario(usuarioGuardado);
        Perfil perfilGuardado = perfilService.guardarPerfil(perfil);


        return usuarioGuardado;
    }















//
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return null;
//    }
//
//    public Usuario buscarUsuarioPorNombre(String username){
//        return usuarioRepository.findTopByUsername(username).orElse(null);
//    }
//
//
//
//    public Usuario guardarUsuario(UsuarioDTO dto){
//
//        Usuario usuario = new Usuario();
//        usuario.setUsername(dto.getUsername());
//        usuario.setPassword(passwordEncoder.encode(dto.getPassword()));
//        usuario.setRol(Rol.PERFIL);
//        return usuarioRepository.save(usuario);
//
//    }


}
