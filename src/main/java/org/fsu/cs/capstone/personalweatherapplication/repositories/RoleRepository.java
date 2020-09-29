package org.fsu.cs.capstone.personalweatherapplication.repositories;

import org.fsu.cs.capstone.personalweatherapplication.models.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<UserRole, Long> {
}
