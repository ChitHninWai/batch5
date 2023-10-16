package com.example.samplerestapi.dao;

import com.example.samplerestapi.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeDao extends JpaRepository<Employee,Integer> {
}
