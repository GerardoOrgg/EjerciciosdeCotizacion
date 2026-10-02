package mx.edu.utez.proyecto1C.service;

import mx.edu.utez.proyecto1C.controller.dto.RequestCotizacionDTO;
import mx.edu.utez.proyecto1C.controller.dto.ResponseCotizacionDTO;
import mx.edu.utez.proyecto1C.customException.BadRequestsException;
import org.springframework.stereotype.Service;

@Service
public class CotizadorService {

    private static final double costobase = 80;
    private static final double costoporkg = 12;
    private static final double volumencargo = 50_000;
    private static final double cargovolumen = 100;
    private static final double valorminseguro = 10_000;
    private static final double porcentajeseguro = 0.02;
    private static final double volumenmax = 1_000_000;

    public ResponseCotizacionDTO cotizar(RequestCotizacionDTO p) {

        String tipo = p.getTipoEnvio().trim().toLowerCase();
        double volumen = p.getLargoCm() * p.getAnchoCm() * p.getAltoCm();
        if (volumen > volumenmax) {
            throw new BadRequestsException("el volumen no puede superar los 1,000,000 cm3");
        }

        double costoporpeso = p.getPesoKg() * costoporkg;
        double cargoporvolumen = volumen > volumencargo ? cargovolumen : 0;
        double acumulado = costobase + costoporpeso + cargoporvolumen;

        double recargo = switch (tipo) {
            case "express" -> acumulado * 0.40;
            case "mismo_dia" -> acumulado * 0.70;
            default -> 0;
        };

        double seguro = p.getValorDeclarado() > valorminseguro
                ? p.getValorDeclarado() * porcentajeseguro
                : 0;

        double total = acumulado + recargo + seguro;

        return new ResponseCotizacionDTO(
                costobase,
                redondear(costoporpeso),
                cargoporvolumen,
                redondear(recargo),
                redondear(seguro),
                redondear(total)
        );
    }

    private double redondear(double valor) {
        return Math.round(valor * 100.0) / 100.0;
    }
}