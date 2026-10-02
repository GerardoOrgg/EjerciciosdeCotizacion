package mx.edu.utez.proyecto1C.controller.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCotizacionDTO {

    @NotBlank(message = "el codigo postal es obligatorio")
    private String codigoPostal;

    @NotNull(message = "el peso es obligatorio")
    @Positive(message = "el peso debe ser mayor a 0")
    @DecimalMax(value = "50", message = "no se aceptan paquetes de mas de 50 kg")
    private Double pesoKg;

    @NotNull(message = "el largo es obligatorio")
    @Positive(message = "el largo debe ser mayor a 0")
    @DecimalMax(value = "150", message = "el largo no puede superar los 150 cm")
    private Double largoCm;

    @NotNull(message = "el ancho es obligatorio")
    @Positive(message = "el ancho debe ser mayor a 0")
    @DecimalMax(value = "150", message = "el ancho no puede superar los 150 cm")
    private Double anchoCm;

    @NotNull(message = "el alto es obligatorio")
    @Positive(message = "el alto debe ser mayor a 0")
    @DecimalMax(value = "150", message = "el alto no puede superar los 150 cm")
    private Double altoCm;

    @NotBlank(message = "el tipo de envio es obligatorio")
    @Pattern(regexp = "(?i)estandar|express|mismo_dia",
            message = "tipo de envio no valido, usa: estandar, express o mismo_dia")
    private String tipoEnvio;

    @NotNull(message = "el valor declarado es obligatorio")
    @PositiveOrZero(message = "el valor declarado no puede ser negativo")
    private Double valorDeclarado;
}