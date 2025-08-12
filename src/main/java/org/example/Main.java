package org.example;

import org.example.domain.*;
import org.example.service.Parqueadero;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Parqueadero parqueadero = new Parqueadero();
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                mostrarMenu();
                String opcion = leerOpcionMenuPrincipal(sc);

                switch (opcion) {
                    case "1" -> {
                        System.out.println("=================================");
                        System.out.println("    Ingreso de vehículo");
                        System.out.println("=================================");
                        System.out.print("Ingrese la placa del vehìculo a estacionar: ");
                        String placa = sc.nextLine();
                        System.out.println("Recibido: \"" + placa + "\"\n");
                        Optional<Vehiculo> existe = parqueadero.buscarVehiculo(placa);
                        if(existe.isPresent()) {
                            System.out.println("Ya hay un vehículo en el parqueadero con la placa " + placa + ", por lo que no se puede completar la operación");
                        } else {
                            mostrarTipoVehiculo();
                            String tipo = leerOpcionTipoVehiculo(sc);
                            System.out.print("Ingrese la marca del vehìculo a estacionar: ");
                            String marca = sc.nextLine().trim();
                            System.out.print("Ingrese el modelo del vehìculo a estacionar: ");
                            String modelo = sc.nextLine().trim();
                            Vehiculo ingreso = null;
                            switch (tipo) {
                                case "1" -> {
                                    System.out.print("Ingrese el tipo de combustible del automóvil: ");
                                    String tipoCombustible = sc.nextLine().trim();
                                    ingreso = new Automovil(placa, marca, modelo, LocalDateTime.now(), tipoCombustible);
                                }
                                case "2" -> {
                                    int cilindraje = leerEntero(sc, "Ingrese el el cilindraje en cc^3: ");
                                    ingreso = new Motocicleta(placa, marca, modelo, LocalDateTime.now(), cilindraje);
                                }
                                case "3" -> {
                                    double capacidadCarga = leerDouble(sc, "Ingrese la capacidad de carga del camión en toneladas: ");
                                    ingreso = new Camion(placa, marca, modelo, LocalDateTime.now(), capacidadCarga);
                                }
                            }
                            parqueadero.registrarIngreso(ingreso);
                            System.out.println("Operación realizada con éxito.");
                        }
                    }
                    case "2" -> {
                        System.out.println("=================================");
                        System.out.println("    Salida vehículo");
                        System.out.println("=================================");
                        System.out.print("Ingresa la placa del vehìculo de salida:  ");
                        String placa = sc.nextLine();
                        System.out.println("Recibido: \"" + placa + "\"\n");
                        Optional<Vehiculo> existe = parqueadero.buscarVehiculo(placa);
                        if(existe.isEmpty()) {
                            System.out.println("No existe un vehículo en el parqueadero con la placa " + placa + ", por lo que no se puede completar la operación");
                        } else {
                            double tarifa = parqueadero.registrarSalida(existe.get());
                            System.out.println("Salida registrada correctamente. El valor a pagar es de $" + tarifa);
                        }
                    }
                    case "3" -> {
                        System.out.println("=================================");
                        System.out.println("    Estado actual parqueadero");
                        System.out.println("=================================");
                        System.out.println("Cantidad de vehículos presentes: " + parqueadero.getVehiculos().size());
                        System.out.println("Detalles adicionales:");
                        parqueadero.getVehiculos().stream()
                                .map(v ->
                                        "   Tipo: " + v.getClass().getSimpleName() + ", Placa: " + v.getPlaca() +
                                                ", Hora Entrada: " + v.getHoraEntrada().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
                                .forEach(System.out::println);
                    }
                    case "4" -> {
                        System.out.println("\nSaliendo del programa. ¡Hasta luego!");
                        return;
                    }
                }
            }
        }
    }

    private static void mostrarTipoVehiculo() {
        System.out.println("Seleeccione el tipo de vehículo");
        System.out.println("1) Automóvil");
        System.out.println("2) Motocicleta");
        System.out.println("3) Camión");
        System.out.print("Elige una opción [1-3]: ");
    }

    private static void mostrarMenu() {
        System.out.println("=================================");
        System.out.println("    Menú Parqueadero");
        System.out.println("=================================");
        System.out.println("1) Ingreso vehículo");
        System.out.println("2) Salida vehículo");
        System.out.println("3) Consultar parqueadero");
        System.out.println("4) Cerrar sistema");
        System.out.print("Elige una opción [1-4]: ");
    }

    /**
     * Lee la opción de menú y valida que sea exactamente 1, 2,  o 4.
     * Rechaza cualquier otro número o texto.
     */
    private static String leerOpcionMenuPrincipal(Scanner sc) {
        while (true) {
            if (!sc.hasNextLine()) {
                System.out.println("\nEntrada finalizada. Saliendo...");
                System.exit(0);
            }
            String entrada = sc.nextLine().trim();
            if (entrada.matches("[1234]")) {
                return entrada;
            }
            System.out.print("Entrada inválida. Debes digitar 1, 2 , 3 o 4: ");
        }
    }

    private static String leerOpcionTipoVehiculo(Scanner sc) {
        while (true) {
            if (!sc.hasNextLine()) {
                System.out.println("\nEntrada finalizada. Saliendo...");
                System.exit(0);
            }
            String entrada = sc.nextLine().trim();
            if (entrada.matches("[123]")) {
                return entrada;
            }
            System.out.print("Entrada inválida. Debes digitar 1, 2 o 3: ");
        }
    }

    public static int leerEntero(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextInt()) {
                int valor = sc.nextInt();
                sc.nextLine();
                return valor;
            } else {
                sc.nextLine();
                System.out.println("Entrada inválida. Debes digitar un número entero.");
            }
        }
    }

    public static double leerDouble(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            if (sc.hasNextDouble()) {
                double valor = sc.nextDouble();
                sc.nextLine();
                return valor;
            } else {
                sc.nextLine();
                System.out.println("Entrada inválida. Debes digitar un número (usa punto como separador decimal).");
            }
        }
    }

}