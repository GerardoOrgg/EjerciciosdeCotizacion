package mx.edu.utez.proyecto1C.controller.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestRentaDTO {

    @NotBlank(message = "el nombre del cliente es obligatorio")
    private String nombreCliente;

    @NotNull(message = "la edad es obligatoria")
    @Min(value = 18, message = "el conductor debe tener al menos 18 años")
    private Integer edadConductor;

    @NotBlank(message = "el tipo de vehiculo es obligatorio")
    @Pattern(regexp = "(?i)compacto|sedan|suv|camioneta",
            message = "tipo de vehiculo no valido, usa: compacto, sedan, suv o camioneta")
    private String tipoVehiculo;

    @NotNull(message = "los dias de renta son obligatorios")
    @Min(value = 1, message = "la renta debe ser de al menos 1 dia")
    @Max(value = 30, message = "la renta no puede superar los 30 dias")
    private Integer diasRenta;

    @NotNull(message = "los kilometros estimados son obligatorios")
    @Min(value = 0, message = "los kilometros no pueden ser negativos")
    @Max(value = 5000, message = "los kilometros estimados no pueden superar 5000")
    private Integer kilometrosEstimados;

    @NotNull(message = "debes indicar si contratas el seguro completo")
    private Boolean seguroCompleto;
}