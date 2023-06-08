package com.reserveappartment.appartmentreserveapp.repos;

import com.reserveappartment.appartmentreserveapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
