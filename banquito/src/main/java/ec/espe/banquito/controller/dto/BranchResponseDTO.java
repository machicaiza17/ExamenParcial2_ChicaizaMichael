package ec.espe.banquito.controller.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BranchResponseDTO {
    private String id;
    private String name;
    private String emailAddress;
    private String phoneNumber;
    private String state;
    private LocalDateTime creationDate;
    private LocalDateTime lastModifiedDate;
    private List<BranchHolidaysDTO> holidays;

    
}
