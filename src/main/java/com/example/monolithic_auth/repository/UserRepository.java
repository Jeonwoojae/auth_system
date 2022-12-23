package com.example.monolithic_auth.repository;

import com.example.monolithic_auth.dto.UserResponseDto;
import com.example.monolithic_auth.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    UserResponseDto findByEmailAndPassword(final String email, final String password);
    Users findByEmail(final String email);
}
