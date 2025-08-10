package org.example.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Parqueadero {
    public final double TARIFA_AUTOMOVIL = 200;
    public final double TARIFA_MOTOCICLETAS = 200;
    public final double TARIFA_CAMIONES = 200;
    private List<Vehiculo> vehiculos;

    public Parqueadero() {
        this.vehiculos = new ArrayList<>();
    }

    public void registrarIngreso(Vehiculo vehiculo) {
        this.vehiculos.add(vehiculo);
    }

    public double registrarSalida(String placa) {
        Optional<Vehiculo> existe = vehiculos.stream()
                .filter(v -> v.getPlaca().equalsIgnoreCase(placa))
                .findFirst();
        if(existe.isEmpty()) {
            throw new NoSuchElementException("No hay vehìculo registrado con la placa: " + placa);
        }
        vehiculos.remove(existe.get());
        long duracion = Duration.between(existe.get().getHoraEntrada(), LocalDateTime.now()).toMinutes();
        double horas = Math.ceil((double) duracion /60);
        if(existe.get() instanceof Automovil) {
            return TARIFA_AUTOMOVIL * horas;
        }
        else if(existe.get() instanceof Motocicleta) {
            return TARIFA_MOTOCICLETAS * horas;
        }else {
            return TARIFA_CAMIONES * horas;
        }
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }
}
