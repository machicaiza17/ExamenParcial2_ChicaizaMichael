package ec.espe.banquito.controller.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateBranchDTO {
    @NotBlank(message = "El nombre de la sucursal es obligatorio")
    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
    private String name;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Email(message = "Correo electrónico inválido")
    private String emailAddress;

    @NotBlank(message = "El número de teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]+$", message = "El teléfono debe contener solo números")
    private String phoneNumber;

    @NotBlank(message = "El estado es obligatorio")
    private String state;
}
