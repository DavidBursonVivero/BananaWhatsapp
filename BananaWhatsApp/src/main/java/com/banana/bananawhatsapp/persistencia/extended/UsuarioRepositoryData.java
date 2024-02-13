package com.banana.bananawhatsapp.persistencia.extended;

import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;


public interface UsuarioRepositoryData extends JpaRepository <Usuario, Integer> {
   /* public Set<Usuario> findByNombre (String nombre);
    public Set<Usuario> findByNombreContains(String nombre);
    public Usuario findByMensaje_Remitente(String remitente);*/





}
