package com.sam.employeecreatorserver.employee;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Employees")
public class Employee {

  @Getter
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  @Getter
  @Setter
  private String firstName;

  @Column
  @Getter
  @Setter
  private String middleName;

  @Column
  @Getter
  @Setter
  private String lastName;

  @Column
  @Getter
  @Setter
  private String email;

  @Column
  @Getter
  @Setter
  private String phone;

  @Column
  @Getter
  @Setter
  private String address;

  @Column
  @Getter
  @Setter
  private String status; //permanent or contract

  @Column
  @Getter
  @Setter
  private Date startDate;

  @Column
  @Getter
  @Setter
  private Date finishDate; //date or string 'on going'

  @Column
  @Getter
  @Setter
  private String type; //full-time or part-time

  @Column
  @Getter
  @Setter
  private Double hoursPerWeek;

  @Column
  @Getter
  @Setter
  private Date createdAt;

  public Employee() {}

  public Employee(
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
    Double hoursPerWeek,
    Date createdAt
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
    this.createdAt = createdAt;
  }
}
