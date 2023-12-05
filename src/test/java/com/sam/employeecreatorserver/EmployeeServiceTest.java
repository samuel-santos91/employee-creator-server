package com.sam.employeecreatorserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.sam.employeecreatorserver.employee.Employee;
import com.sam.employeecreatorserver.employee.EmployeeCreateDTO;
import com.sam.employeecreatorserver.employee.EmployeeRepository;
import com.sam.employeecreatorserver.employee.EmployeeService;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @Mock
  private ModelMapper mapper;

  @InjectMocks
  private EmployeeService employeeService;

  @Test
  void getAll_ReturnsAllData() {
    employeeService.getAll();
    Mockito.verify(employeeRepository).findAll();
  }

  @Test
  void createsEmployee_ValidEmployee_AddsToDB() {
    EmployeeCreateDTO dto = new EmployeeCreateDTO(
      "name",
      "middle",
      "last",
      "email@email.au",
      "+61999999999",
      "address 123",
      "permanent",
      new Date(2020 - 10 - 10),
      new Date(2021 - 10 - 10),
      null,
      "full-time",
      20.0
    );

    Employee employee = new Employee(
      "name",
      "middle",
      "last",
      "email@email.au",
      "+61999999999",
      "address 123",
      "permanent",
      new Date(2020 - 10 - 10),
      new Date(2021 - 10 - 10),
      null,
      "full-time",
      20.0,
      null
    );

    BDDMockito.given(mapper.map(dto, Employee.class)).willReturn(employee);

    this.employeeService.createEmployee(dto);

    ArgumentCaptor<Employee> employeeArgument = ArgumentCaptor.forClass(
      Employee.class
    );

    Mockito.verify(employeeRepository).save(employeeArgument.capture());

    assertNotEquals(null, employeeArgument.getValue().getCreatedAt());
    assertEquals(employee, employeeArgument.getValue());
  }

  @Test
  void deleteById_ExistingId_ReturnsTrue() {
    Long id = 123l;

    Employee employee = new Employee(
      "name",
      "middle",
      "last",
      "email@email.au",
      "+61999999999",
      "address 123",
      "permanent",
      new Date(2020 - 10 - 10),
      new Date(2021 - 10 - 10),
      null,
      "full-time",
      20.0,
      new Date()
    );

    BDDMockito
      .given(employeeRepository.findById(id))
      .willReturn(Optional.of(employee));

    Boolean deleted = this.employeeService.deleteById(id);

    ArgumentCaptor<Employee> employeeArg = ArgumentCaptor.forClass(
      Employee.class
    );

    Mockito.verify(employeeRepository).delete(employeeArg.capture());

    assertTrue(deleted);
    assertEquals(employee, employeeArg.getValue());
  }

  @Test
  void deleteById_NonExistingId_ReturnsFalse() {
    Long id = 123l;

    BDDMockito
      .given(employeeRepository.findById(id))
      .willReturn(Optional.empty());
    Boolean deleted = this.employeeService.deleteById(id);
    assertFalse(deleted);
  }

  @Test
  void updateById_ExistingId_ReturnsFoundEmployee() {
    Long id = 123l;

    Employee existingEmployee = new Employee(
      "name",
      "middle",
      "last",
      "email@email.au",
      "+61999999999",
      "address 123",
      "permanent",
      new Date(2020 - 10 - 10),
      new Date(2021 - 10 - 10),
      null,
      "part-time",
      20.0,
      new Date()
    );

    EmployeeCreateDTO updatedEmployee = new EmployeeCreateDTO(
      "updatedName",
      "middle",
      "last",
      "email@email.au",
      "+61999999999",
      "address 123",
      "permanent",
      new Date(2020 - 10 - 10),
      new Date(2021 - 10 - 10),
      null,
      "full-time",
      20.0
    );

    Employee updatedEmployeeFromDB = new Employee(
      "updatedName",
      "middle",
      "last",
      "email@email.au",
      "+61999999999",
      "address 123",
      "permanent",
      new Date(2020 - 10 - 10),
      new Date(2021 - 10 - 10),
      null,
      "full-time",
      20.0,
      new Date()
    );

    BDDMockito
      .given(employeeRepository.findById(id))
      .willReturn(Optional.of(existingEmployee));

    BDDMockito
      .doAnswer(invocation -> {
        Employee destination = invocation.getArgument(1);

        destination.setFirstName(updatedEmployee.getFirstName());
        destination.setType(updatedEmployee.getType());

        return null;
      })
      .when(mapper)
      .map(updatedEmployee, existingEmployee);

    BDDMockito
      .given(employeeRepository.save(existingEmployee))
      .willReturn(updatedEmployeeFromDB);

    Optional<Employee> updatedOptional =
      this.employeeService.updateById(id, updatedEmployee);

    assertTrue(updatedOptional.isPresent());

    Employee updated = updatedOptional.get();

    assertNotNull(updated);
    assertEquals(updatedEmployee.getFirstName(), updated.getFirstName());
    assertEquals(updatedEmployee.getType(), updated.getType());

    ArgumentCaptor<Employee> employeeArgument = ArgumentCaptor.forClass(
      Employee.class
    );
    Mockito.verify(employeeRepository).save(employeeArgument.capture());

    assertEquals(
      updatedEmployee.getFirstName(),
      employeeArgument.getValue().getFirstName()
    );
    assertEquals(
      updatedEmployee.getType(),
      employeeArgument.getValue().getType()
    );
  }
}
