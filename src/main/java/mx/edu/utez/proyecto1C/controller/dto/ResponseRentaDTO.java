package mx.edu.utez.proyecto1C.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRentaDTO {
    private double costoRenta;
    private double cargoKilometrosAdicionales;
    private double cargoPorEdad;
    private double seguro;
    private double descuento;
    private double total;
}