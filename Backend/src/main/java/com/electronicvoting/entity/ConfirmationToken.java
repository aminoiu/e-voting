package com.electronicvoting.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "confirmation_token", schema = "voting_data")
public class ConfirmationToken {

    @Id
    @Column(name="token_id")
    private long tokenid;

    @Column(name="confirmation_token")
    private String confirmationToken;

    @Column(name="created_date")
    private Timestamp createdDate;

    @Column(name="user_id")
    private long user_id;

    public ConfirmationToken(Users user) {
        this.user_id = user.getId();
        createdDate = new Timestamp(System.currentTimeMillis());
        confirmationToken = UUID.randomUUID().toString();
    }
}
