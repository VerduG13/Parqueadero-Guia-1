package org.example.domain;

import java.time.LocalDateTime;

public class Camion extends Vehiculo {
    private double capacidadCarga;

    public Camion(String placa, String marca, String modelo, LocalDateTime horaEntrada, double capacidadCarga) {
        super(placa, marca, modelo, horaEntrada);
        this.capacidadCarga = capacidadCarga;
    }

    public double getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(double capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }
}
