package supun.com.main.controller;




import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import supun.com.main.exception.ResourceNotFoundException;
import supun.com.main.model.Employee;
import supun.com.main.model.MeetingRoom;
import supun.com.main.repository.EmployeeRepository;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    @PostMapping("/employee")
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

    //delete an employee
    @DeleteMapping("/employees/{id}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") int employeeId)
            throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        logger.info("----- Employee Deleted Successfully ------");
        response.put("Employee Deleted Successfully", Boolean.TRUE);
        return response;
    }

}
