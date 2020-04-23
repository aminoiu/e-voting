package com.electronicvoting.entity;

import javax.persistence.*;

@Entity
@Table(name="users_role",schema = "voting_data")
public class UsersRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    @Column(name = "user_id")
    long userId;
    @Column(name = "role_id")
    long roleId;
}
