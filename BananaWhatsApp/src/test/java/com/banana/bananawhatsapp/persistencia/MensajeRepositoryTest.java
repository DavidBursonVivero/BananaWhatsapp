package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.extended.MensajeRepositoryData;
import com.banana.bananawhatsapp.util.DBUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class MensajeRepositoryTest {

    @Autowired
    private ApplicationContext context;
    @Autowired
    IUsuarioRepository repoUsuario;
     @Autowired
     MensajeRepositoryData repoMensaje;

    @BeforeEach
    void cleanAndReloadData() {
        DBUtil.reloadDB();
    }

    @Test
    @Order(1)
    void dadoUnMensajeValido_cuandoCrear_entoncesMensajeValido() throws Exception {
        Usuario remitente = repoUsuario.obtener(1);
        Usuario destinatario = repoUsuario.obtener(2);

        Mensaje message = new Mensaje(null, 1, 2, "De acuerdo Juana. Un saludo.", LocalDate.now());

        repoMensaje.save(message);
        assertThat(message, notNullValue());
        assertThat(message.getId(), greaterThan(0));
    }

    @Test
    @Order(2)
    @Transactional
    void dadoUnMensajeNOValido_cuandoCrear_entoncesExcepcion() throws Exception {


        Mensaje message = new Mensaje(null, 1, 2, "SMS < 10", LocalDate.now());
        assertThrows(Exception.class, () -> {
            repoMensaje.save(message);
        });
    }

    @Test
    @Order(3)
    @Transactional
    void dadoUnUsuarioValido_cuandoObtener_entoncesListaMensajes() throws Exception {
        Usuario user = repoUsuario.obtener(1);

        List<Imensaje> userMessages = repoMensaje.findByAllMensajeUser(1);
        assertNotNull(userMessages);
    }

    @Test
    @Order(4)
    @Transactional
    void dadoUnUsuarioNOValido_cuandoObtener_entoncesExcepcion() throws Exception {
        Usuario user = new Usuario(1, null, null, null, false);

        assertThrows(UsuarioException.class, () -> {
            //List<Mensaje> userMessages = repoMensaje.obtener(user);
        });
    }

    @Test
    @Transactional
    @Order(5)
    void dadoUnRemitenteValido_cuandoBorrarEntre_entoncesOK() throws Exception {
        Usuario remitente = repoUsuario.obtener(1);
        Usuario destinatario = repoUsuario.obtener(2);
    repoMensaje.deleteMessagesByUsers(1, 2);
        boolean borrarChat = true;
        assertTrue(borrarChat);
    }
/*rtsss
    @Test
    @Order(6)
    void dadoUnRemitenteNOValido_cuandoBorrarEntre_entoncesExcepcion() throws Exception {
        Usuario remitente = repoUsuario.obtener(1);
        remitente.setActivo(false);
        Usuario destinatario = repoUsuario.obtener(2);

        assertThrows(UsuarioException.class, () -> {
            repoMensaje.borrarEntre(remitente, destinatario);
        });
    }

    @Test
    @Order(7)
    void dadoUnUsuarioValido_cuandoBorrarTodos_entoncesOK() throws Exception {
        Usuario user = repoUsuario.obtener(1);

        boolean borrarChat = repoMensaje.borrarTodos(user);
        assertTrue(borrarChat);
    }

    @Test
    @Order(8)
    void dadoUnUsuarioNOValido_cuandoBorrarTodos_entoncesExcepcion() throws Exception {
        Usuario user = new Usuario(1, null, null, null, true);

        assertThrows(UsuarioException.class, () -> {
            repoMensaje.borrarTodos(user);
        });
    }
*/
}