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
    private List<BranchHolidaysDTO> holidays;
    public void setCreationDate(LocalDateTime creationDate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCreationDate'");
    }
    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setLastModifiedDate'");
    }
}
