package com.sam.employeecreatorserver.employee;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  public List<Employee> getAll() {
    return this.employeeRepository.findAll();
  }

  public Optional<Employee> getById(Long id) {
    Optional<Employee> foundEmployee = employeeRepository.findById(id);
    return foundEmployee;
  }
}
