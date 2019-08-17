package org.hlamito.repository;

import org.hlamito.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends CrudRepository<User , Long> {
  User findByName(String name);
    User  findByEmail(String email);
	
}