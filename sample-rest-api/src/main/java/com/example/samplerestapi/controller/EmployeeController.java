package com.example.samplerestapi.controller;


import com.example.samplerestapi.dao.EmployeeDao;
import com.example.samplerestapi.entity.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeDao employeeDao;
    @GetMapping("/all")
    public List<EmployeeDto> findAll(){
        return  employeeDao.findAll()
                .stream()
                .map(e -> new EmployeeDto(e.getId(),e.getFirstName(),e.getLastName(),e.getEmail()))
                .collect(Collectors.toList());
    }
    record RequestEmployee(
            @JsonProperty("first_name") String firstName,
            @JsonProperty("last_name") String lastName,
            String email
    ){

    }
    @PostMapping("/create")
    public ResponseEntity<EmployeeDto>
    createEmployee(@RequestBody RequestEmployee emp){
        Employee employee= employeeDao
                .save(new Employee(emp.firstName,emp.lastName,emp.email));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(
                        new EmployeeDto(employee.getId(),
                                employee.getFirstName(),
                                employee.getLastName(),
                                employee.getEmail())
                );
    }


    record EmployeeDto(int id,
                       @JsonProperty("first_name") String firstName,
                       @JsonProperty("last_name") String lastName,
                       String email){}


}
