package ec.espe.banquito.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document(collection = "branches")
public class Branch {

    @Id
    private String id;
    private String emailAddress;
    private String name;
    private String phoneNumber;
    private String state;
    private LocalDateTime creationDate;
    private LocalDateTime lastModifiedDate;
    private List<BranchHolidays> branchHolidays;

    public Branch(String name, String emailAddress, String phoneNumber, String state) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.state = state;
        this.creationDate = LocalDateTime.now();
        this.lastModifiedDate = LocalDateTime.now();
    }

}
