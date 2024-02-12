package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.modelos.Usuario;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

@Repository
@Transactional
public class UsuarioRepoImpl implements IUsuarioRepository{

    @PersistenceContext
    EntityManager em;
    @Override
    public Usuario obtener(int id) throws SQLException {
        return em.find(Usuario.class, id);
    }

    @Override
    public Usuario crear(Usuario usuario) throws SQLException {
        em.getTransaction().begin();

        Usuario currentUser = em.find(Usuario.class, usuario.getId());

        currentUser.setNombre(usuario.getNombre());
        currentUser.setEmail(usuario.getEmail());
        currentUser.setActivo(usuario.isActivo());
        currentUser.setAlta(usuario.getAlta());
        currentUser.setMensajes(usuario.getMensajes());

        em.persist(currentUser);
        em.getTransaction().commit();

        return currentUser;
 }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Usuario actualizar(Usuario usuario) throws SQLException {
        usuario.valido();
        return em.merge(usuario);
    }

    @Override
    public boolean borrar(Usuario usuario) throws SQLException {
        Usuario usuarioBorrado = em.find(Usuario.class, usuario.getId());
        em.remove(usuarioBorrado);

        return false;
    }

    @Override
    public Set<Usuario> obtenerPosiblesDestinatarios(Integer id, Integer max) throws SQLException {
         Query query = em.createQuery("SELECT u FROM Usuario u WHERE u.id != :id")
                                  .setParameter("id", id)
                                  .setMaxResults(max);
        List resultList = query.getResultList();
        return new HashSet<>(resultList);
    }

}


