package companyEmployee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private int id;
    private String title;
    private String description;
    private String priority;
    private String status;
    private Date dueDate;
    private int assignedTo;
    private Date createdAt;
    private Date updatedAt;
}