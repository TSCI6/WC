package com.whichcontact.core.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.whichcontact.core.entity.User;

@Repository
public interface UserLoginRepository extends JpaRepository < User, Long > {
 User findByNameAndPassword(String username, String password);
 User findByEmail(String email);
User findById(int id);
}