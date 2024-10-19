package com.any.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.any.entity.User;

public interface UserRepository extends JpaRepository<User, Long>
{

}
