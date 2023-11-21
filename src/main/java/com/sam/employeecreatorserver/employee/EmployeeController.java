package com.sam.employeecreatorserver.employee;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping
  public ResponseEntity<Employee> createEmployee(
    @Valid @RequestBody EmployeeCreateDTO data
  ) {
    Employee newEmployee = this.employeeService.createEmployee(data);
    return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Employee> deleteById(@PathVariable Long id) {
    boolean deleted = this.employeeService.deleteById(id);

    if (deleted == true) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    throw new Error("Employee Not Found");
  }

  @PatchMapping("/{id}")
  public ResponseEntity<Employee> updateById(
    @PathVariable Long id,
    @Valid @RequestBody EmployeeCreateDTO data
  ) {
    Optional<Employee> updated = this.employeeService.updateById(id, data);

    if (updated.isPresent()) {
      return new ResponseEntity<Employee>(updated.get(), HttpStatus.OK);
    }

    throw new Error("Employee Not Found");
  }
}
