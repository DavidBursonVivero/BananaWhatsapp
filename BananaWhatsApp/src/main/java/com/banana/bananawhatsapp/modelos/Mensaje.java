package com.banana.bananawhatsapp.modelos;

import com.banana.bananawhatsapp.exceptions.MensajeException;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

//@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

@Entity
public class Mensaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Usuario remitente;

    @ManyToOne
    private Usuario destinatario;

    private String cuerpo;

    private LocalDate fecha;

    public Mensaje(Integer id, Usuario remitente, Usuario destinatario, String cuerpo, LocalDate fecha) {
        this.id = id;
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.cuerpo = cuerpo;
        this.fecha = fecha;
    }

    private boolean validarFecha() {
        return this.fecha != null && this.fecha.compareTo(LocalDate.now()) <= 0;
    }

    public boolean valido() throws MensajeException {
        if (remitente != null
                && destinatario != null
                && remitente.valido()
                && destinatario.valido()
                && cuerpo != null
                && cuerpo.length() > 10
                && validarFecha()
        ) return true;
        else throw new MensajeException("Mensaje no valido");
    }
}