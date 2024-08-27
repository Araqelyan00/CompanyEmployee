package companyEmployee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    private int companyId;
    private String companyName;
    private String companyCountry;
}
