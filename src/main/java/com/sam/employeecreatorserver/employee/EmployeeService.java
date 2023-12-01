package com.sam.employeecreatorserver.employee;

import jakarta.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class EmployeeService {

  @Autowired
  private EmployeeRepository employeeRepository;

  @Autowired
	private ModelMapper modelMapper;

  public List<Employee> getAll() {
    return this.employeeRepository.findAll();
  }

  public Optional<Employee> getById(Long id) {
    Optional<Employee> foundEmployee = employeeRepository.findById(id);
    return foundEmployee;
  }

  public Employee createEmployee(EmployeeCreateDTO data) {

    Employee newEmployee = modelMapper.map(data, Employee.class);
    newEmployee.setCreatedAt(new Date());
    Employee created = this.employeeRepository.save(newEmployee);
    return created;
  }

  public boolean deleteById(Long id) {
    Optional<Employee> foundEmployee = this.getById(id);

    if (foundEmployee.isPresent()) {
      this.employeeRepository.delete(foundEmployee.get());
      return true;
    }
    return false;
  }

  public Optional<Employee> updateById(Long id, EmployeeCreateDTO data) {
    Optional<Employee> foundEmployee = this.getById(id);

    if (foundEmployee.isPresent()) {
      Employee toUpdate = foundEmployee.get();

      modelMapper.map(data, toUpdate);
     
      Employee updatedEmployee = this.employeeRepository.save(toUpdate);

      return Optional.of(updatedEmployee);
    }

    return foundEmployee;
  }
}
