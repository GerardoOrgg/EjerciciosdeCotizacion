package mx.edu.utez.proyecto1C.controller;

import jakarta.validation.Valid;
import mx.edu.utez.proyecto1C.controller.dto.RequestCotizacionDTO;
import mx.edu.utez.proyecto1C.controller.dto.RequestHospedajeDTO;
import mx.edu.utez.proyecto1C.controller.dto.RequestRentaDTO;
import mx.edu.utez.proyecto1C.controller.dto.ResponseCotizacionDTO;
import mx.edu.utez.proyecto1C.controller.dto.ResponseHospedajeDTO;
import mx.edu.utez.proyecto1C.controller.dto.ResponseRentaDTO;
import mx.edu.utez.proyecto1C.service.CotizadorService;
import mx.edu.utez.proyecto1C.service.HospedajeService;
import mx.edu.utez.proyecto1C.service.RentaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin({"*"}) //Todos los origenes
@RequestMapping("/my-services")
public class Mycontroller {

    private final CotizadorService cotizadorService;
    private final RentaService rentaService;
    private final HospedajeService hospedajeService;

    public Mycontroller(
                        CotizadorService cotizadorService,
                        RentaService rentaService,
                        HospedajeService hospedajeService) {
        this.cotizadorService = cotizadorService;
        this.rentaService = rentaService;
        this.hospedajeService = hospedajeService;
    }

    @PostMapping("/cotizar-envio")
    public ResponseEntity<ResponseCotizacionDTO> cotizar(
            @Valid @RequestBody RequestCotizacionDTO payload) {

        return ResponseEntity.ok(cotizadorService.cotizar(payload));
    }

    @PostMapping("/cotizar-renta")
    public ResponseEntity<ResponseRentaDTO> cotizarRenta(
            @Valid @RequestBody RequestRentaDTO payload) {

        return ResponseEntity.ok(rentaService.cotizar(payload));
    }

    @PostMapping("/cotizar-hospedaje")
    public ResponseEntity<ResponseHospedajeDTO> cotizarHospedaje(
            @Valid @RequestBody RequestHospedajeDTO payload) {

        return ResponseEntity.ok(hospedajeService.cotizar(payload));
    }
}