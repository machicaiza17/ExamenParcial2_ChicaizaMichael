package ec.espe.banquito.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UpdateBranchPhoneDTO {
    @NotBlank(message = "El número de teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]+$", message = "El teléfono debe contener solo números")
    private String phoneNumber;
}