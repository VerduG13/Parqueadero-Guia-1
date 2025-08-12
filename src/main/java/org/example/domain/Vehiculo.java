package org.example.domain;

import java.time.LocalDateTime;

public abstract class Vehiculo {

    private String placa;
    private String marca;
    private String modelo;
    private LocalDateTime horaEntrada;

    public Vehiculo(String placa, String marca, String modelo, LocalDateTime horaEntrada) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.horaEntrada = horaEntrada;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "placa='" + placa + '\'' +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", horaEntrada=" + horaEntrada +
                '}';
    }
}
