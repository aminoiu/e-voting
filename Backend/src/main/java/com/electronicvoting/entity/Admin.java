package com.electronicvoting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.sql.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "admin",schema = "voting_data")
public class Admin {
    @Id
    @Column(name = "admin_id")
    private String adminId;
    @Column(name = "name", nullable = false)
    private String name;
    @Email
    @Size(min = 5, max = 100)
    @Column(name = "email", length = 100, unique = true, nullable = false)
    private String email;
    @Column(name = "work_place", nullable = false)
    private String workPlace;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "street", nullable = false)
    private String street;
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "phone_number", length = 20, unique = true, nullable = false)
    private String phoneNumber;
    @Column(name = "hash_password", nullable = false)
    private String hashPassword;
//    @Column(name = "salt")
//    private String salt;

}
