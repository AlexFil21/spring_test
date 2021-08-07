package com.family.family.repo;

import com.family.family.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    List<User> findAll();

    @Query(value = "SELECT * FROM USER", nativeQuery = true)
    List<User> findAllUsers();


}
