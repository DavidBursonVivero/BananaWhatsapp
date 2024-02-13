package com.banana.bananawhatsapp.persistencia.extended;

import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.persistencia.Imensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MensajeRepositoryData extends JpaRepository <Mensaje, Integer> {
    @Query(nativeQuery = true, value = "SELECT m.id as id, m.cuerpo as cuerpo, m.fecha as fecha, m.to_user as destinatario, m.from_user as remitente FROM mensaje m WHERE m.to_user = :id OR m.from_user = :id")
    public List<Imensaje> findByAllMensajeUser(@Param("id") Integer id);

    @Modifying
    @Query(value = "DELETE FROM mensaje WHERE (to_user = :idTo or from_user = :idFrom )AND (to_user = :idTo or from_user = :idFrom)", nativeQuery = true)
    void deleteMessagesByUsers(@Param("idTo") Integer idTo, @Param("idFrom") Integer idFrom);


}
