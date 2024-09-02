package companyEmployee.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    private int employeeId;
    private String employeeName;
    private String employeeSurname;
    private String employeeEmail;
    private String employeePicName;
    private Company company;
}
