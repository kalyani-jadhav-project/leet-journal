package com.hardik.auth.repository;

import com.hardik.auth.model.Users;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends ListCrudRepository<Users, Integer> {
}
