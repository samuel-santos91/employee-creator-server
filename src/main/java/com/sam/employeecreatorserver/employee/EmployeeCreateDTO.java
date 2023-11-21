package com.sam.employeecreatorserver.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class EmployeeCreateDTO {

  @Getter
  @Setter
  @NotEmpty
  @Size(min = 2, message = "user name should have at least 2 characters")
  private String firstName;

  @Getter
  @Setter
  private String middleName;

  @Getter
  @Setter
  @NotEmpty
  private String lastName;

  @Getter
  @Setter
  @NotEmpty
  @Email
  private String email;

  @Getter
  @Setter
  @Pattern(regexp = "\\+61\\d{9}", message = "Must be an Australian number")
  private String phone;

  @Getter
  @Setter
  @NotEmpty
  private String address;

  @Getter
  @Setter
  @NotEmpty
  private String status; //permanent or contract

  @Getter
  @Setter
  @NotEmpty
  private Date startDate;

  @Getter
  @Setter
  //search for a good constraint
  private Date finishDate; //if null = on going

  @Getter
  @Setter
  @NotEmpty
  private String type; //full-time or part-time

  @Getter
  @Setter
  @Positive(message = "Hours per week must be a positive number")
  private Number hoursPerWeek;

  public EmployeeCreateDTO() {};

  public EmployeeCreateDTO(
    String firstName,
    String middleName,
    String lastName,
    String email,
    String phone,
    String address,
    String status,
    Date startDate,
    Date finishDate,
    String type,
    Number hoursPerWeek
  ) {
    this.firstName = firstName;
    this.middleName = middleName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.address = address;
    this.status = status;
    this.startDate = startDate;
    this.finishDate = finishDate;
    this.type = type;
    this.hoursPerWeek = hoursPerWeek;
  }
}
