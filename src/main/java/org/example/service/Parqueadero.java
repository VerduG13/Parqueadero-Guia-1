package org.example.service;

import org.example.domain.Automovil;
import org.example.domain.Motocicleta;
import org.example.domain.Vehiculo;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Parqueadero {
    public final double TARIFA_AUTOMOVIL = 15000;
    public final double TARIFA_MOTOCICLETAS = 7500;
    public final double TARIFA_CAMIONES = 30000;
    private List<Vehiculo> vehiculos;

    public Parqueadero() {
        this.vehiculos = new ArrayList<>();
    }

    public Optional<Vehiculo> buscarVehiculo(String placa) {
        return vehiculos.stream()
                .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
                .findFirst();
    }

    public void registrarIngreso(Vehiculo vehiculo) {
        this.vehiculos.add(vehiculo);
    }

    public double registrarSalida(Vehiculo vehiculo) {
        vehiculos.remove(vehiculo);
        long duracion = Duration.between(vehiculo.getHoraEntrada(), LocalDateTime.now()).toMinutes();
        double horas = Math.ceil((double) duracion /60);
        if(vehiculo instanceof Automovil) {
            return TARIFA_AUTOMOVIL * horas;
        }
        else if(vehiculo instanceof Motocicleta) {
            return TARIFA_MOTOCICLETAS * horas;
        }else {
            return TARIFA_CAMIONES * horas;
        }
    }

    public String darEstadoActual() {
        return "";
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }
}
