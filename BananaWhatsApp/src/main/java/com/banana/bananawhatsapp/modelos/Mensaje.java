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

    @Column(name="from_user")
    private  Integer remitente;

    @Column(name="to_user")
    private Integer destinatario;

    private String cuerpo;

    private LocalDate fecha;

    public Mensaje(Integer id, Integer remitente, Integer destinatario, String cuerpo, LocalDate fecha) {
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
                && remitente != null
                && destinatario!= null
                && cuerpo != null
                && cuerpo.length() > 10
                && validarFecha()
        ) return true;
        else throw new MensajeException("Mensaje no valido");
    }
}
