package com.sl.safajobs.servicios;

import com.sl.safajobs.dto.RegistroDTO;
import com.sl.safajobs.enumerados.Rol;
import com.sl.safajobs.modelos.Usuario;
import com.sl.safajobs.repositorios.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService implements UserDetailsService {


    private UsuarioRepository usuarioRepository;

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

        return usuarioRepository.save(nuevoUsuario);
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
