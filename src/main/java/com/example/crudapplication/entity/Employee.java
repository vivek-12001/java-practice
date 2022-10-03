package com.example.crudapplication.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Data
public class Employee {

    Integer empId;
    String empName;
    Integer empSalary;
}
