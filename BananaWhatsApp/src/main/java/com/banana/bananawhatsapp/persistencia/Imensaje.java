package com.banana.bananawhatsapp.persistencia;

import java.time.LocalDate;
import java.util.Date;

public interface Imensaje {

    Integer getId();
    String getCuerpo();
    LocalDate getFecha();
    String getDestinatario();
    String getRemitente();
}


