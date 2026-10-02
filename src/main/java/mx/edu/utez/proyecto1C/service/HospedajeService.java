package mx.edu.utez.proyecto1C.service;

import mx.edu.utez.proyecto1C.controller.dto.RequestHospedajeDTO;
import mx.edu.utez.proyecto1C.controller.dto.ResponseHospedajeDTO;
import mx.edu.utez.proyecto1C.customException.BadRequestsException;
import org.springframework.stereotype.Service;

@Service
public class HospedajeService {

    private static final double costoindividual = 700;
    private static final double costodoble = 1100;
    private static final double costosuite = 1800;

    private static final int capacidadindividual = 1;
    private static final int capacidaddoble = 2;
    private static final int capacidadsuite = 4;

    private static final double porcentajebaja = 0.10;
    private static final double porcentajealta = 0.25;
    private static final double costodesayuno = 150;
    private static final double costoestacionamiento = 100;
    private static final int nochesdescuento = 7;
    private static final double porcentajeestancia = 0.08;
    private static final double porcentajeimpuesto = 0.04;

    public ResponseHospedajeDTO cotizar(RequestHospedajeDTO p) {

        String tipo = p.getTipoHabitacion().trim().toLowerCase();
        String temporada = p.getTemporada().trim().toLowerCase();
        int noches = p.getNumeroNoches();
        int huespedes = p.getNumeroHuespedes();

        int capacidad = switch (tipo) {
            case "individual" -> capacidadindividual;
            case "doble" -> capacidaddoble;
            default -> capacidadsuite;
        };

        if (huespedes > capacidad) {
            throw new BadRequestsException("la habitacion " + tipo + " admite maximo "
                    + capacidad + " huesped(es)");
        }

        double costopornoche = switch (tipo) {
            case "individual" -> costoindividual;
            case "doble" -> costodoble;
            default -> costosuite;
        };

        double costohospedaje = costopornoche * noches;

        double descuentotemporada = temporada.equals("baja") ? costohospedaje * porcentajebaja : 0;
        double cargotemporada = temporada.equals("alta") ? costohospedaje * porcentajealta : 0;
        double descuentoestancia = noches >= nochesdescuento ? costohospedaje * porcentajeestancia : 0;
        double desayuno = p.getIncluyeDesayuno() ? huespedes * noches * costodesayuno : 0;
        double estacionamiento = p.getIncluyeEstacionamiento() ? noches * costoestacionamiento : 0;
        double subtotal = costohospedaje - descuentotemporada + cargotemporada
                - descuentoestancia + desayuno + estacionamiento;
        double impuesto = subtotal * porcentajeimpuesto;
        double total = subtotal + impuesto;

        return new ResponseHospedajeDTO(
                redondear(costohospedaje),
                redondear(descuentotemporada),
                redondear(cargotemporada),
                redondear(descuentoestancia),
                redondear(desayuno),
                redondear(estacionamiento),
                redondear(subtotal),
                redondear(impuesto),
                redondear(total)
        );
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}