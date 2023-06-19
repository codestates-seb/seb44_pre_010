package com.rainbow.sof.domain.user.repository;

import com.rainbow.sof.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
