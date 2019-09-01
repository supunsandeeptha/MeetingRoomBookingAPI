package supun.com.main.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supun.com.main.exception.ResourceNotFoundException;
import supun.com.main.model.Employee;
import supun.com.main.repository.EmployeeRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    //Injecting the Employee Repository
    @Autowired
    private EmployeeRepository employeeRepository;


    // creating a new employee
    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    //get all the employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return  employeeRepository.findAll();
    }

    //get an employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") int employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        return ResponseEntity.ok().body(employee);
    }

}
