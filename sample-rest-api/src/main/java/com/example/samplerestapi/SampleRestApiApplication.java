package com.example.samplerestapi;

import com.example.samplerestapi.dao.EmployeeDao;
import com.example.samplerestapi.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@SpringBootApplication
@RequiredArgsConstructor
public class SampleRestApiApplication {
    private final EmployeeDao employeeDao;
    @Transactional
    @Bean
    public ApplicationRunner runner(){
        return r ->{
            Employee e1=new Employee("John","Doe","john@gmail.com");
            Employee e2=new Employee("John","William","william@gmail.com");
            Employee e3=new Employee("Thomas","Hardy","john@gmail.com");
            Employee e4=new Employee("Charles","Dickens","john@gmail.com");
            Employee e5=new Employee("Henery","James","john@gmail.com");

            Stream.of(e1,e2,e3,e4,e5)
                    .forEach(employeeDao::save);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(SampleRestApiApplication.class, args);
    }

}
