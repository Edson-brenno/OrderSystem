package com.spring.Repository;

import com.spring.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email LIKE %:email%")
    List<User> findByEmailLike(@Param("email") String email);
}
