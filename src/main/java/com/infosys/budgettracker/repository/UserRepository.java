package com.infosys.budgettracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.infosys.budgettracker.model.UserEntity;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);
}
