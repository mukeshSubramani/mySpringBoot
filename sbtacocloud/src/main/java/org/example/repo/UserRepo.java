package org.example.repo;

import org.example.domains.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User,Long> {

    User findByUsername(String userName);
}
