package org.example.domain;

import java.time.LocalDateTime;

public class Motocicleta extends Vehiculo {
    private String cilindraje;
    public Motocicleta(String placa, String marca, String modelo, LocalDateTime horaEntrada, String cilindraje) {
        super(placa, marca, modelo, horaEntrada);
        this.cilindraje = cilindraje;
    }

    public String getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(String cilindraje) {
        this.cilindraje = cilindraje;
    }
}
