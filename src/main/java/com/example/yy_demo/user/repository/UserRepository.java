package com.example.yy_demo.user.repository;

import com.example.yy_demo.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{
}
