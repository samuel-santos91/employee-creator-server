package com.sam.employeecreatorserver.employee;

import jakarta.transaction.Transactional;
import java.util.Date;
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

  public Employee createEmployee(EmployeeCreateDTO data) {
    //refactor to model mapper
    String firstName = data.getFirstName().trim();
    String middleName = data.getMiddleName().trim();
    String lastName = data.getLastName().trim();
    String email = data.getEmail().trim();
    String phone = data.getPhone().replaceAll("\\s+", "");
    String address = data.getAddress().trim();
    String status = data.getStatus();
    Date startDate = data.getStartDate();
    Date finishDate = data.getFinishDate();
    String type = data.getType();
    Double hoursPerWeek = data.getHoursPerWeek();
    Date createdAt = new Date();

    Employee newEmployee = new Employee(
      firstName,
      middleName,
      lastName,
      email,
      phone,
      address,
      status,
      startDate,
      finishDate,
      type,
      hoursPerWeek,
      createdAt
    );
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

      //refactor to model mapper
      toUpdate.setFirstName(data.getFirstName());
      toUpdate.setMiddleName(data.getMiddleName());
      toUpdate.setLastName(data.getLastName());
      toUpdate.setEmail(data.getEmail());
      toUpdate.setPhone(data.getPhone());
      toUpdate.setAddress(data.getAddress());
      toUpdate.setStatus(data.getStatus());
      toUpdate.setStartDate(data.getStartDate());
      toUpdate.setFinishDate(data.getFinishDate());
      toUpdate.setType(data.getType());
      toUpdate.setHoursPerWeek(data.getHoursPerWeek());

      Employee updatedEmployee = this.employeeRepository.save(toUpdate);

      return Optional.of(updatedEmployee);
    }

    return foundEmployee;
  }
}
