package com.sam.employeecreatorserver;

import com.sam.employeecreatorserver.employee.Employee;
import com.sam.employeecreatorserver.employee.EmployeeRepository;
import com.sam.employeecreatorserver.employee.EmployeeService;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

  @Mock
  private EmployeeRepository employeeRepository;

  @InjectMocks
  private EmployeeService employeeService;

  @Test
  void getAll_ReturnsAllData() {

    employeeService.getAll();
    Mockito.verify(employeeRepository).findAll();
  }

//   @Test
//   void createsEmployee_ValidEmployee_AddsToDB() {

//     EmployeeCreateDTO = new 
//   }
}
