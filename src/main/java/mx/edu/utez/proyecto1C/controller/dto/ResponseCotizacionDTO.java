package mx.edu.utez.proyecto1C.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCotizacionDTO {
    private double costoBase;
    private double costoPorPeso;
    private double cargoPorVolumen;
    private double recargoTipoEnvio;
    private double seguro;
    private double total;
}