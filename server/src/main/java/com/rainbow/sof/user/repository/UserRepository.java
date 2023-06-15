package com.rainbow.sof.user.repository;

import com.rainbow.sof.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
