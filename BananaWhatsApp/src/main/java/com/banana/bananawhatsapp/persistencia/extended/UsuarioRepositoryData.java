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

    @Query("SELECT m.* FROM mensaje m WHERE m.FROM_user LIKE ?1 orm.to_user LIKE ?1")
    public List <Mensaje> allMensajeByUserID (Integer id);

    @Query("SELECT u.nombre From usuario u JOIN usuario_mensaje um on (um.usuario_id = u.id) JOIN mensaje m on (um.mensaje_id = m.id) WHERE m.id LIKE %?1")
    public List <String> findDestinatarioByMensajeId (Integer id);



}
