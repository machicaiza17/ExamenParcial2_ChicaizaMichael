package ec.espe.banquito.controller;

import ec.espe.banquito.controller.dto.*;
import ec.espe.banquito.service.BranchService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.time.LocalDate;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/v1/branches")
public class BranchController {
    private static final Logger log = LoggerFactory.getLogger(BranchController.class);
    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @Operation(summary = "Obtener todas las sucursales")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de sucursales obtenida exitosamente",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = BranchResponseDTO.class))),
        @ApiResponse(responseCode = "500", description = "Error en el servidor",
            content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<BranchResponseDTO>> getAllBranches() {
        try {
            log.info("Obteniendo todas las sucursales");
            return ResponseEntity.ok(branchService.getAllBranches());
        } catch (Exception e) {
            log.error("No se pudieron obtener todas las sucursales", e);
            return ResponseEntity.status(500).build();
        }
    }

    @Operation(summary = "Crear una sucursal sin feriados")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucursal creada exitosamente",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = BranchResponseDTO.class))),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos",
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Error al crear la sucursal",
            content = @Content)
    })
    @PostMapping
    public ResponseEntity<BranchResponseDTO> createBranch(@Valid @RequestBody CreateBranchDTO dto) {
        try {
            log.info("Creando una nueva sucursal: {}", dto.getName());
            return ResponseEntity.ok(branchService.createBranch(dto));
        } catch (Exception e) {
            log.error("No se pudo crear una sucursal sin feriados", e);
            return ResponseEntity.status(500).build();
        }
    }
    @Operation(summary = "Obtener una sucursal por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Sucursal obtenida exitosamente",
            content = @Content(mediaType = "application/json",
            schema = @Schema(implementation = BranchResponseDTO.class))),
        @ApiResponse(responseCode = "404", description = "Sucursal no encontrada",
            content = @Content),
        @ApiResponse(responseCode = "500", description = "Error en la obtención",
            content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<BranchResponseDTO> getBranchById(@PathVariable String id) {
        try {
            log.info("Obteniendo sucursal con ID: {}", id);
        return ResponseEntity.ok(branchService.getBranchById(id));
        } catch (Exception e) {
            log.error(id, e);
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/{id}/phone")
    public ResponseEntity<BranchResponseDTO> updateBranchPhone(
            @PathVariable String id,
            @Valid @RequestBody UpdateBranchPhoneDTO dto) {
        return ResponseEntity.ok(branchService.updateBranchPhone(id, dto));
    }

    @DeleteMapping("/{id}/holidays")
    public ResponseEntity<Void> deleteHolidaysFromBranch(@PathVariable String id) {
        branchService.deleteHolidaysFromBranch(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/is-holiday")
    public ResponseEntity<Boolean> isDateHoliday(@PathVariable String id, @RequestParam LocalDate date) {
        return ResponseEntity.ok(branchService.isDateHoliday(id, date));
    }
}
