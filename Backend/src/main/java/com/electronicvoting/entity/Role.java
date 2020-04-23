package com.electronicvoting.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "roles",schema = "voting_data")
public class Role {
    @Id
    @Column(name = "id")
    Integer id;

    @Column(name="name")
    String name;
}
