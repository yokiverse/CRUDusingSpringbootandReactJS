package com.springcrudwithreact.emplyee.controller;

import com.springcrudwithreact.emplyee.entity.Employee;
import com.springcrudwithreact.emplyee.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor//use to inject service package
@CrossOrigin("*")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/employee")
    public Employee postEmployee(@RequestBody Employee employee){
        return employeeService.postEmployee(employee);
    }

    @GetMapping("/getallemployee")
    public List<Employee> getAllEmployee(){
        return employeeService.getAllEmployee();
    }

@DeleteMapping("/employee/{id}")
public ResponseEntity<?> deleteEmployee(@PathVariable long id) {
    try {
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee with ID " + id + " deleted successfully", HttpStatus.OK);
    } catch (EntityNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
@GetMapping("/employee/{id}")
public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
    Employee employee = employeeService.getEmployeeById(id);
    if (employee == null) {
        return ResponseEntity.notFound().build();
    } else {
        return ResponseEntity.ok(employee);
    }
}


@PatchMapping("/employee/{id}")
public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employee)
{
    Employee updatedEmployee = employeeService.updateEmployee(id, employee);
    if(updatedEmployee == null){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
    else{
        return ResponseEntity.ok(updatedEmployee);
    }
}
}
