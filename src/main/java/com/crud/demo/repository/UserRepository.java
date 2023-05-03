package com.crud.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.demo.dto.User;

public interface UserRepository extends JpaRepository<User,Long>{

}
