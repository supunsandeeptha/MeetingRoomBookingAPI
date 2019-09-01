package supun.com.main.controller;




import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supun.com.main.exception.ResourceNotFoundException;
import supun.com.main.model.Employee;
import supun.com.main.repository.EmployeeRepository;

import javax.validation.Valid;
import java.util.List;



@RestController
// api end point
@RequestMapping("/api/v1")
public class EmployeeController {

    //logging purposes
    Logger logger = LoggerFactory.getLogger(EmployeeController.class);


    //Injecting the Employee Repository
    @Autowired
    private EmployeeRepository employeeRepository;


    // creating a new employee
    @PostMapping("/employees")
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        //logging
        logger.info("------- Employees creating method ----------");
        return employeeRepository.save(employee);
    }

    //get all the employees
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        logger.info("------- Employees Returning method -------");
        return  employeeRepository.findAll();
    }

    //get an employee by id
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") int employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
        logger.info("------ Find Employees By ID. Employee of Id" + employeeId + " is " + employee);
        return ResponseEntity.ok().body(employee);
    }

}
