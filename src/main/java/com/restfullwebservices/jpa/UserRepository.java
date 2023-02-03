package com.restfullwebservices.jpa;

import com.restfullwebservices.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository< User,Integer> {
}
