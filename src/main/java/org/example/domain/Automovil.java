package org.example.domain;

import java.time.LocalDateTime;

public class Automovil extends Vehiculo {

    private String tipoCombustible;

    public Automovil(String placa, String marca, String modelo, LocalDateTime horaEntrada, String tipoCombustible) {
        super(placa, marca, modelo, horaEntrada);
        this.tipoCombustible = tipoCombustible;
    }

    public Automovil() {

    }

    @Override
    public String toString() {
        return super.toString() + "Automovil{" +
                "tipoCombustible='" + tipoCombustible + '\'' +
                '}';
    }

    public String getTipoCombustible() {
        return tipoCombustible;
    }

    public void setTipoCombustible(String tipoCombustible) {
        this.tipoCombustible = tipoCombustible;
    }
}
