package mx.edu.utez.proyecto1C.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseHospedajeDTO {
    private double costoHospedaje;
    private double descuentoTemporada;
    private double cargoTemporada;
    private double descuentoEstancia;
    private double desayuno;
    private double estacionamiento;
    private double subtotal;
    private double impuesto;
    private double total;
}