package com.edward.beltexam.repositories;

import com.edward.beltexam.models.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    
    public User findByEmail(String email);
}