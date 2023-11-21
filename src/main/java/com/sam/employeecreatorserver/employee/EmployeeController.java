package com.sam.employeecreatorserver.employee;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

  @Autowired
  private EmployeeService employeeService;

  @GetMapping
  public ResponseEntity<List<Employee>> getAllEmployees() {
    List<Employee> allEmployees = this.employeeService.getAll();
    return new ResponseEntity<>(allEmployees, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Employee> getElementById(@PathVariable Long id) {
    Optional<Employee> employee = this.employeeService.getById(id);

    if (employee.isPresent()) {
      return new ResponseEntity<>(employee.get(), HttpStatus.OK);
    } else {
      throw new Error(String.format("Employee with id: %d not found", id));
    }
  }
}
