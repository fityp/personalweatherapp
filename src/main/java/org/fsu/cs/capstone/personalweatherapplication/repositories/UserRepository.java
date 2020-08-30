package org.fsu.cs.capstone.personalweatherapplication.repositories;

import org.fsu.cs.capstone.personalweatherapplication.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
