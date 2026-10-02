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
public class RequestHospedajeDTO {

    @NotBlank(message = "el nombre del huesped es obligatorio")
    private String nombreHuesped;

    @NotBlank(message = "el tipo de habitacion es obligatorio")
    @Pattern(regexp = "(?i)individual|doble|suite",
            message = "tipo de habitacion no valido, usa: individual, doble o suite")
    private String tipoHabitacion;

    @NotNull(message = "el numero de noches es obligatorio")
    @Min(value = 1, message = "debe ser al menos 1 noche")
    @Max(value = 30, message = "el numero de noches no puede superar 30")
    private Integer numeroNoches;

    @NotNull(message = "el numero de huespedes es obligatorio")
    @Min(value = 1, message = "debe haber al menos 1 huesped")
    private Integer numeroHuespedes;

    @NotBlank(message = "la temporada es obligatoria")
    @Pattern(regexp = "(?i)baja|regular|alta",
            message = "temporada no valida, usa: baja, regular o alta")
    private String temporada;

    @NotNull(message = "debes indicar si incluye desayuno")
    private Boolean incluyeDesayuno;

    @NotNull(message = "debes indicar si incluye estacionamiento")
    private Boolean incluyeEstacionamiento;
}