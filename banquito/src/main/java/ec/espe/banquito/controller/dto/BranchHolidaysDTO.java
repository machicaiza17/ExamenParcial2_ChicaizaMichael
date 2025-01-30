package ec.espe.banquito.controller.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BranchHolidaysDTO {
    @NotNull(message = "La fecha del feriado es obligatoria")
    private LocalDate date;

    @NotBlank(message = "El nombre del feriado es obligatorio")
    private String name;
}
