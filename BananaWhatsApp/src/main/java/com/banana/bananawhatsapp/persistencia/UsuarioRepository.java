package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.modelos.Usuario;
import lombok.Data;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Repository
@Profile("dev")
public class UsuarioRepository implements IUsuarioRepository {

    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioRepository(){
        usuarios.add(new Usuario(3, "Pepe", "pepe@email.com", null, true));
    }

    @Override
    public Usuario obtener(int id) throws SQLException {
        return null;
    }

    @Override
    public Usuario crear(Usuario usuario) throws SQLException {
        this.usuarios.add(usuario);
        return usuario;
    }

    @Override
    public Usuario actualizar(Usuario usuario) throws SQLException {
        return null;
    }

    @Override
    public boolean borrar(Usuario usuario) throws SQLException {
        return false;
    }

    @Override
    public Set<Usuario> obtenerPosiblesDestinatarios(Integer id, Integer max) throws SQLException {
        return null;
    }
}
