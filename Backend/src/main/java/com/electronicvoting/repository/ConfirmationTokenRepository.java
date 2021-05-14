package com.electronicvoting.repository;

import com.electronicvoting.entity.ConfirmationToken;
import com.electronicvoting.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, String> {
    ConfirmationToken findByConfirmationToken(String confirmationToken);

}
