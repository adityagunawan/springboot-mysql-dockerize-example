package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepo employeeRepo;

    @GetMapping(path = "/add/{name}/{title}")
    public ResponseEntity save (@PathVariable("name") String name, @PathVariable("title") String title) {
        log.info("add data employee : {} {}", name, title);
        try {
            Employee employee = new Employee(name, title);
            employeeRepo.save(employee);
            log.info("data saved!");
            return ResponseEntity.ok(employee);
        } catch (Exception e) {
            log.error("terjadi kesalahan");
            return ResponseEntity.ok("internal server error");
        }
    }

    @GetMapping(path = "/get-all")
    public ResponseEntity getAll() {
        log.info("get all employee data");
        return ResponseEntity.ok(employeeRepo.findAll());
    }
}
