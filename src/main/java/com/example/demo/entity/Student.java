package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {

  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
      name = "UUID",
      strategy = "org.hibernate.id.UUIDGenerator",
      parameters = {
          @Parameter(
              name = "uuid_gen_strategy_class",
              value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
          )
      }
  )
  private UUID id;
  private String name;
  private String email;


  @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL)
  @JoinColumn(name = "student_address", referencedColumnName = "id")
  private Set<Address> addresses;

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", addresses=" + addresses +
        '}';
  }
}
