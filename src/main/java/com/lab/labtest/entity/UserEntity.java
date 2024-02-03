package com.lab.labtest.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "user")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @Column(name="name")
    private  String name;
  
    @Column(name="email")
    private  String email;

    @Column(name="age")
    private  Integer age;

    @Column(name="designation")
    private  String designation;

    @Column(name="related_data")
    private  String related_data;

    public void setRelatedData(String relatedData) {
        this.related_data = relatedData;
    }
}
