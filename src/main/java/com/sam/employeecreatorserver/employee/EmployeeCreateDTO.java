package com.sam.employeecreatorserver.employee;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class EmployeeCreateDTO {

  @Getter
  @Setter
  @NotBlank
  @Size(min = 2, message = "user name should have at least 2 characters")
  private String firstName;

  @Getter
  @Setter
  private String middleName;

  @Getter
  @Setter
  @NotBlank
  private String lastName;

  @Getter
  @Setter
  @NotBlank
  @Email
  private String email;

  @Getter
  @Setter
  @NotBlank
  @Pattern(regexp = "\\+61\\s*\\d{9}", message = "Must be an Australian number")
  private String phone;

  @Getter
  @Setter
  private String address;

  @Getter
  @Setter
  @NotBlank
  private String status; //permanent or contract

  @Getter
  @Setter
  @NotNull
  private Date startDate;

  @Getter
  @Setter
  private Date finishDate; //if null => 'ongoing'

  @Getter
  @Setter
  private String ongoing; //if null => finishDate

  @Getter
  @Setter
  @NotBlank
  private String type; //full-time or part-time

  @Getter
  @Setter
  @Max(value = 50, message = "Hours per week must be at most 50")
  private Double hoursPerWeek;

  public EmployeeCreateDTO() {}

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
    String ongoing,
    String type,
    Double hoursPerWeek
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
    this.ongoing = ongoing;
    this.type = type;
    this.hoursPerWeek = hoursPerWeek;
  }
}
