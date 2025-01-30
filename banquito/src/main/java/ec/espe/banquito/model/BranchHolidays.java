package ec.espe.banquito.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BranchHolidays {
    private LocalDate date;
    private String name;

    public BranchHolidays(LocalDate date, String name) {
        this.date = date;
        this.name = name;
    }
}
