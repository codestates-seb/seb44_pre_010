package com.rainbow.sof.domain.user.repository;

import com.rainbow.sof.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

public interface UserRepository extends JpaRepository<User,Long> {
=======
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732
}
