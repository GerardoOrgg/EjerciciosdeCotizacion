package mx.edu.utez.proyecto1C.service;

import mx.edu.utez.proyecto1C.controller.dto.RequestRentaDTO;
import mx.edu.utez.proyecto1C.controller.dto.ResponseRentaDTO;
import mx.edu.utez.proyecto1C.customException.BadRequestsException;
import org.springframework.stereotype.Service;

@Service
public class RentaService {

    private static final double costocompacto = 550;
    private static final double costosedan = 700;
    private static final double costosuv = 950;
    private static final double costocamioneta = 1200;

    private static final int kmincluidosdia = 100;
    private static final double costokmadicional = 4;
    private static final double porcentajeedad = 0.15;
    private static final double costoseguridia = 180;
    private static final int diasdescuento = 7;
    private static final double porcentajedescuento = 0.10;
    private static final int edadminimacamioneta = 25;

    public ResponseRentaDTO cotizar(RequestRentaDTO p) {

        String tipo = p.getTipoVehiculo().trim().toLowerCase();
        int edad = p.getEdadConductor();
        int dias = p.getDiasRenta();

        if (tipo.equals("camioneta") && edad < edadminimacamioneta) {
            throw new BadRequestsException("para rentar una camioneta el conductor debe tener al menos 25 años");
        }

        double costodiario = switch (tipo) {
            case "compacto" -> costocompacto;
            case "sedan" -> costosedan;
            case "suv" -> costosuv;
            default -> costocamioneta;
        };

        double costorenta = costodiario * dias;
        int kmincluidos = dias * kmincluidosdia;
        int kmadicionales = Math.max(0, p.getKilometrosEstimados() - kmincluidos);
        double cargokm = kmadicionales * costokmadicional;

        double cargoedad = edad <= 24 ? (costorenta + cargokm) * porcentajeedad : 0;

        double seguro = p.getSeguroCompleto() ? costoseguridia * dias : 0;

        double descuento = dias >= diasdescuento ? costorenta * porcentajedescuento : 0;

        double total = costorenta + cargokm + cargoedad + seguro - descuento;

        return new ResponseRentaDTO(
                redondear(costorenta),
                redondear(cargokm),
                redondear(cargoedad),
                redondear(seguro),
                redondear(descuento),
                redondear(total)
        );
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}