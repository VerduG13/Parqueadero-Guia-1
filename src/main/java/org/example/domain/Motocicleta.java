package org.example.domain;

import java.time.LocalDateTime;

public class Motocicleta extends Vehiculo {
    private int cilindraje;
    public Motocicleta(String placa, String marca, String modelo, LocalDateTime horaEntrada, int cilindraje) {
        super(placa, marca, modelo, horaEntrada);
        this.cilindraje = cilindraje;
    }

    public int getCilindraje() {
        return cilindraje;
    }

    public void setCilindraje(int cilindraje) {
        this.cilindraje = cilindraje;
    }

    @Override
    public String toString() {
        return "Motocicleta{" +
                "cilindraje=" + cilindraje +
                '}';
    }
}
