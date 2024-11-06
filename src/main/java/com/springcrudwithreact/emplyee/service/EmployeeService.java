package com.springcrudwithreact.emplyee.service;

import com.springcrudwithreact.emplyee.entity.Employee;
import com.springcrudwithreact.emplyee.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor //used to inject employee repo
public class EmployeeService {
        private final EmployeeRepository employeeRepository;

        public Employee postEmployee(Employee employee){

            return employeeRepository.save(employee);
        }

        public List<Employee> getAllEmployee(){
            return employeeRepository.findAll();
        }


        public void deleteEmployee(Long id){
            if(!employeeRepository.existsById(id)){
                throw new EntityNotFoundException("Employee with this id" + id + "not found");
            }
            employeeRepository.deleteById(id);
        }

        public Employee getEmployeeById(Long id){
            return employeeRepository.findById(id).orElse(null);
        }

        public Employee updateEmployee(Long id, Employee employee){
            Optional<Employee> optionalEmployee = employeeRepository.findById(id);
            if(optionalEmployee.isPresent()){
                Employee existingEmployee = optionalEmployee.get();

                existingEmployee.setEmail(employee.getEmail());
                existingEmployee.setName(employee.getName());
                existingEmployee.setPhone(employee.getPhone());
                existingEmployee.setDepartment(employee.getDepartment());

                return employeeRepository.save(existingEmployee);
            }
            return null;
        }
}
