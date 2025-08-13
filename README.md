# Parqueadero-Guia-1
Proyecto de Parqueadero en Java curso Desarrollo de Software EAN
Objetivo y alcance
Se implementa un sistema de consola que:
- Registra entrada y salida de vehículos.
- Calcula el costo según tipo y tiempo de estadía, cobrando fracciones como hora completa.
- Permite consultar el estado actual (vehículos presentes).

Alcance: ejecución en memoria (sin BD), zona horaria del sistema, interfaz solo por consola.

Estructura del proyecto y diseño
Paquetes
org.example.domain: clases del dominio (herencia).

org.example.service: lógica de negocio del parqueadero.

org.example: capa de interfaz de usuario (consola) en Main.

Clases de dominio (org.example.domain)
Vehiculo (abstracta)
Atributos: placa:String, marca:String, modelo:String, horaEntrada:LocalDateTime.

Responsabilidad: concentrar los datos comunes a cualquier vehículo.

Decisiones:

horaEntrada no tiene setter: se define al ingreso y no se modifica, evitando inconsistencias en el cálculo.

toString() básico para depuración.

Automovil → Vehiculo
Atributo adicional: tipoCombustible:String.

Uso: permite diferenciar reglas futuras; actualmente participa en la tarifa por instanceof.

Motocicleta → Vehiculo
Atributo adicional: cilindraje:int.

Camion → Vehiculo
Atributo adicional: capacidadCarga:double (toneladas).

Servicio (org.example.service.Parqueadero)
Atributos:

List<Vehiculo> vehiculos: vehículos presentes actualmente.

Tarifas por hora (constantes públicas):

TARIFA_AUTOMOVIL = 15000

TARIFA_MOTOCICLETAS = 7500

TARIFA_CAMIONES = 30000

Métodos principales:

Optional<Vehiculo> buscarVehiculo(String placa): búsqueda por placa case-insensitive.

void registrarIngreso(Vehiculo vehiculo): añade el vehículo a la lista (la validación de duplicados la hace Main antes de llamar).

double registrarSalida(Vehiculo vehiculo): remueve de la lista y calcula el cobro (ver §3).

List<Vehiculo> getVehiculos(): expone la lista actual para la consulta desde Main.

Decisión de diseño: se optó por centralizar el cálculo de tarifas en el servicio usando instanceof (en lugar de polimorfismo con tarifaHora() en cada subclase). Ventaja: todas las tarifas en un solo lugar, simple para esta guía.

Interfaz de usuario (org.example.Main)
Menú principal (opciones 1–4) y menú de tipo de vehículo (1–3).

Flujo de ingreso:

Solicita placa y verifica con parqueadero.buscarVehiculo(placa).

Si no existe, pide tipo (1–3), marca, modelo y el atributo específico:

Automóvil: tipoCombustible

Motocicleta: cilindraje

Camión: capacidadCarga

Crea la subclase con LocalDateTime.now() como horaEntrada y llama a registrarIngreso.

Flujo de salida:

Solicita placa y busca el vehículo.

Si existe, llama registrarSalida(v) y muestra el valor a pagar.

Consulta de estado:

Muestra vehiculos.size() y lista cada vehículo con:

v.getClass().getSimpleName() como tipo,

placa,

hora de entrada formateada HH:mm:ss (DateTimeFormatter.ofPattern("HH:mm:ss")).

3) Cálculo de cobro (tarifas y redondeo)
En Parqueadero.registrarSalida:

Se elimina el vehículo de la lista (vehiculos.remove(vehiculo)).

Clases derivadas:

Automovil (+ tipoCombustible) → implementado.

Motocicleta (+ cilindraje) → implementado.

Camion (+ capacidadCarga) → implementado.

Clase Parqueadero:

Registrar entrada y salida

Cálculo del costo por tipo + fracciones como hora completa 

Interfaz de usuario (consola):

Ingresar vehículo → opción 1.

Registrar salida → opción 2.

Consultar estado → opción 3.

Evidencia mínima de funcionamiento (sesión tipo)
markdown
Copy
Edit
=================================
    Menú Parqueadero
=================================
1) Ingreso vehículo
2) Salida vehículo
3) Consultar parqueadero
4) Cerrar sistema
Elige una opción [1-4]: 1
=================================
    Ingreso de vehículo
=================================
Ingrese la placa del vehìculo a estacionar: ABC123
Recibido: "ABC123"

Seleeccione el tipo de vehículo
1) Automóvil
2) Motocicleta
3) Camión
Elige una opción [1-3]: 1
Ingrese la marca del vehìculo a estacionar: Mazda
Ingrese el modelo del vehìculo a estacionar: 3
Ingrese el tipo de combustible del automóvil: gasolina
Operación realizada con éxito.
(La salida y la consulta siguen la misma secuencia descrita.)
Formato de hora en consulta: HH:mm:ss (DateTimeFormatter).

Código y mensajes en español.
